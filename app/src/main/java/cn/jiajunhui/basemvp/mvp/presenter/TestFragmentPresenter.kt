package cn.jiajunhui.basemvp.mvp.presenter

import android.os.Handler
import cn.jiajunhui.basemvp.mvp.ITestFragmentPresenter
import cn.jiajunhui.mvp.mvp.BasePresenter

class TestFragmentPresenter : BasePresenter<ITestFragmentPresenter.ITestFragmentView>(), ITestFragmentPresenter {

    override fun loadData() {
        mView?.stateLoading(null)
        Handler().postDelayed({
            mView?.stateSuccess(null)
        },1500)
    }

}