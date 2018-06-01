package cn.jiajunhui.basemvp

import android.view.LayoutInflater
import android.view.View
import cn.jiajunhui.basemvp.base.BaseAppActivity
import cn.jiajunhui.basemvp.mvp.IMainPresenter
import cn.jiajunhui.basemvp.mvp.factory.MainPresenterFactory
import cn.jiajunhui.basemvp.mvp.presenter.MainPresenter
import cn.jiajunhui.mvp.mvp.PresenterFactory

class MainActivity : BaseAppActivity<MainPresenter, IMainPresenter.IMainView>(),IMainPresenter.IMainView {

    private var mPresenter:MainPresenter?=null

    override fun onInitViews() {

    }

    override fun getPresenterFactory(): PresenterFactory<MainPresenter> {
        return MainPresenterFactory()
    }

    override fun onPresenterPrepared(presenter: MainPresenter) {
        mPresenter = presenter
    }

    override fun onInitData() {
        mPresenter?.loadData()
    }

    override fun onErrorRetry() {
        super.onErrorRetry()
        mPresenter?.loadData()
    }

    fun openFragment(view:View){
        val fragment = TestFragment.createInstance(null)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commitAllowingStateLoss()
    }

    override fun onCreateContentView(): View {
        return LayoutInflater.from(this).inflate(R.layout.activity_main, null)
    }

}
