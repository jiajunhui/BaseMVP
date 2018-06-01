package cn.jiajunhui.basemvp.mvp

import cn.jiajunhui.mvp.mvp.IView

interface IMainPresenter {

    fun loadData()

    interface IMainView : IView{

    }

}