package cn.jiajunhui.basemvp.mvp.factory

import cn.jiajunhui.basemvp.mvp.presenter.TestFragmentPresenter
import cn.jiajunhui.mvp.mvp.PresenterFactory

class TestFragmentPresenterFactory : PresenterFactory<TestFragmentPresenter> {
    override fun create(): TestFragmentPresenter {
        return TestFragmentPresenter()
    }
}