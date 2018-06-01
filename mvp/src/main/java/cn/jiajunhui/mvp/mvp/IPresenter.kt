package cn.jiajunhui.mvp.mvp

interface IPresenter<V> {

    fun onViewAttached(v:V)

    fun onViewDetached()

    fun onDestroy()

}