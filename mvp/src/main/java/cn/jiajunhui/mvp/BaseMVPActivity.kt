package cn.jiajunhui.mvp

import cn.jiajunhui.mvp.mvp.IPresenter
import cn.jiajunhui.mvp.mvp.IView
import cn.jiajunhui.mvp.mvp.PresenterFactory

abstract class BaseMVPActivity<P:IPresenter<V>, V:IView> : BaseLayoutActivity() {

    var presenter:P?=null

    override fun onBeforeSetContentView() {
        super.onBeforeSetContentView()
        val presenterFactory = getPresenterFactory()
        presenter = presenterFactory.create()
        onPresenterPrepared(presenter!!)
    }

    override fun onStart() {
        super.onStart()
        presenter?.onViewAttached(getPresenterView())
    }

    override fun onStop() {
        super.onStop()
        presenter?.onViewDetached()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    abstract fun getPresenterFactory():PresenterFactory<P>

    abstract fun onPresenterPrepared(presenter:P)

    private fun getPresenterView(): V {
        return this as V
    }

}