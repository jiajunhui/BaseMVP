package cn.jiajunhui.mvp

import android.os.Bundle
import android.support.v4.util.SparseArrayCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import cn.jiajunhui.mvp.extension.ActivityUtilsInterface
import cn.jiajunhui.mvp.layoutcontroller.*
import cn.jiajunhui.mvp.mvp.IView

abstract class BaseLayoutActivity : AppCompatActivity(),
        LayoutPart, OnLayoutEventListener, IView, ActivityUtilsInterface {

    private val typeContentView:Int = Int.MAX_VALUE

    private var hasInit:Boolean=false
    private var mRootView:View?=null
    private var mLayoutController:LayoutController?=null

    private var mViewArrays:SparseArrayCompat<View>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewArrays = SparseArrayCompat()
        val contentView = onCreateContentView()
        mLayoutController = onBindLayoutController()
        if(mLayoutController!=null){
            mViewArrays?.put(typeContentView, contentView)
            mRootView = mLayoutController!!.getView()
            mLayoutController?.setOnLayoutEventListener(this)
            mLayoutController?.attachLayoutPart(this)
        }else{
            mRootView = contentView
        }
        onBeforeSetContentView()
        setContentView(mRootView!!)
        onInitViews()
        onInitSaveInstance(savedInstanceState)
    }

    open fun onBeforeSetContentView(){

    }

    override fun onEvent(code: Int, bundle: Bundle?) {
        when(code){
            LAYOUT_EVENT_CODE_ERROR_RETRY -> {
                onErrorRetry()
            }
        }
    }

    open fun onErrorRetry(){

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        if(!hasInit){
            hasInit = true
            onInitData()
        }
    }

    abstract fun onInitViews()

    open fun onInitSaveInstance(savedInstanceState: Bundle?){

    }

    abstract fun onInitData()

    abstract fun onBindLayoutController():LayoutController

    override fun getUserContentView(): View {
        var contentView = mViewArrays?.get(typeContentView)
        return contentView!!
    }

    override fun getLoadingView(): View? = onCreateLoadingView()

    override fun getErrorView(): View? = onCreateErrorView()

    override fun getExtraView(type: Int): View?{
        var extraView = onCreateExtraView(type)
        if(extraView!=null){
            return extraView
        }else{
            extraView = onCreateExtraView(type)
            mViewArrays?.put(type, extraView)
        }
        return extraView
    }

    abstract fun onCreateContentView():View

    open fun onCreateLoadingView():View?=null

    open fun onCreateErrorView():View?=null

    open fun onCreateExtraView(type: Int):View?=null

    fun getRootView() = mRootView

    override fun stateLoading(bundle: Bundle?) {
        mLayoutController?.loading(bundle)
    }

    override fun stateSuccess(bundle: Bundle?) {
        mLayoutController?.success(bundle)
    }

    override fun stateError(bundle: Bundle?) {
        mLayoutController?.error(bundle)
    }

    override fun stateExtra(type: Int, bundle: Bundle?) {
        mLayoutController?.extra(type, bundle)
    }

}