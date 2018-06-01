package cn.jiajunhui.mvp.mvp

interface PresenterFactory<P> {

    fun create():P

}