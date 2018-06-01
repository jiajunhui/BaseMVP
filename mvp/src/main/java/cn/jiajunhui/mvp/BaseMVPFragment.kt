package cn.jiajunhui.mvp

import android.content.Context
import cn.jiajunhui.mvp.mvp.IPresenter
import cn.jiajunhui.mvp.mvp.IView
import cn.jiajunhui.mvp.mvp.PresenterFactory

abstract class BaseMVPFragment<P:IPresenter<V>,V:IView> : BaseLayoutFragment(){

    var presenter:P?=null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val presenterFactory = getPresenterFactory()
        presenter = presenterFactory.create()
        onPresenterPrepared(presenter!!)
        presenter?.onViewAttached(getPresenterView())
    }

    override fun onDetach() {
        super.onDetach()
        presenter?.onViewDetached()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.onDestroy()
    }

    abstract fun getPresenterFactory():PresenterFactory<P>

    abstract fun onPresenterPrepared(presenter:P)

    private fun getPresenterView():V{
        return this as V
    }

}