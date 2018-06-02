package cn.jiajunhui.mvp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.util.SparseArrayCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jiajunhui.mvp.extension.ActivityUtilsInterface
import cn.jiajunhui.mvp.layoutcontroller.LAYOUT_EVENT_CODE_ERROR_RETRY
import cn.jiajunhui.mvp.layoutcontroller.LayoutController
import cn.jiajunhui.mvp.layoutcontroller.LayoutPart
import cn.jiajunhui.mvp.layoutcontroller.OnLayoutEventListener
import cn.jiajunhui.mvp.mvp.IView

abstract class BaseLayoutFragment : Fragment(),
        LayoutPart, OnLayoutEventListener, IView, ActivityUtilsInterface {

    private val typeContentView:Int = Int.MAX_VALUE

    private var hasInit:Boolean=false
    private var mRootView:View?=null
    private var mLayoutController: LayoutController?=null

    private var mViewArrays: SparseArrayCompat<View>?=null

    private var mContext:Context?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(mViewArrays==null)
            mViewArrays = SparseArrayCompat()
        if(mRootView==null){
            val contentView = onCreateContentView(inflater, container)
            mLayoutController = onBindLayoutController()
            if(mLayoutController!=null){
                mViewArrays?.put(typeContentView, contentView)
                mRootView = mLayoutController!!.getView()
                mLayoutController?.setOnLayoutEventListener(this)
                mLayoutController?.attachLayoutPart(this)
            }else{
                mRootView = contentView
            }
            onInitViews()
            onInitSaveInstance(savedInstanceState)
        }else{
            val parent = mRootView?.parent
            if(parent!=null && parent is ViewGroup){
                parent.removeView(mRootView)
            }
        }
        return mRootView
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!hasInit){
            hasInit = true
            onInitData()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(hasInit){
            if(isVisibleToUser){
                onVisible()
            }else{
                onInVisible()
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hasInit){
            if(hidden){
                onInVisible()
            }else{
                onVisible()
            }
        }
    }

    open fun onVisible(){

    }

    open fun onInVisible(){

    }

    abstract fun onInitViews()

    open fun onInitSaveInstance(savedInstanceState: Bundle?){

    }

    abstract fun onInitData()

    abstract fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?):View

    abstract fun onBindLayoutController():LayoutController

    override fun getUserContentView(): View {
        val contentView = mViewArrays?.get(typeContentView)
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