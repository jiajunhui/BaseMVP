package cn.jiajunhui.mvp.mvp

abstract class BasePresenter<V> : IPresenter<V> {

    var mView:V?=null

    override fun onViewAttached(v: V) {
        mView = v
    }

    override fun onViewDetached() {
        mView = null
    }

    override fun onDestroy() {
        mView = null
    }

}