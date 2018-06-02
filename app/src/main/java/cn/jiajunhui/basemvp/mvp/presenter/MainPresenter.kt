package cn.jiajunhui.basemvp.mvp.presenter

import android.os.Handler
import cn.jiajunhui.basemvp.mvp.IMainPresenter
import cn.jiajunhui.mvp.mvp.BasePresenter

class MainPresenter : BasePresenter<IMainPresenter.IMainView>(), IMainPresenter {

    var mLoadNum:Int = 0

    override fun loadData() {
        mView?.stateLoading(null)
        Handler().postDelayed({
            if(mLoadNum%2==0){
                mLoadNum++
                mView?.stateError(null)
            }else{
                mLoadNum++
                mView?.onSuccess()
                mView?.stateSuccess(null)
            }
        },3000)
    }

}