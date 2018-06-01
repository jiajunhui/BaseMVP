package cn.jiajunhui.basemvp.mvp.factory

import cn.jiajunhui.basemvp.mvp.presenter.MainPresenter
import cn.jiajunhui.mvp.mvp.PresenterFactory

class MainPresenterFactory : PresenterFactory<MainPresenter> {

    override fun create(): MainPresenter {
        return MainPresenter()
    }

}