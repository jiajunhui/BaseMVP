package cn.jiajunhui.basemvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jiajunhui.basemvp.base.BaseAppFragment
import cn.jiajunhui.basemvp.mvp.ITestFragmentPresenter
import cn.jiajunhui.basemvp.mvp.factory.TestFragmentPresenterFactory
import cn.jiajunhui.basemvp.mvp.presenter.TestFragmentPresenter
import cn.jiajunhui.mvp.mvp.PresenterFactory

class TestFragment : BaseAppFragment<TestFragmentPresenter, ITestFragmentPresenter.ITestFragmentView>()
        , ITestFragmentPresenter.ITestFragmentView{

    private var mPresenter:TestFragmentPresenter?=null

    companion object {
        fun createInstance(bundle: Bundle?):TestFragment{
            val fragment = TestFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getPresenterFactory(): PresenterFactory<TestFragmentPresenter> {
        return TestFragmentPresenterFactory()
    }

    override fun onPresenterPrepared(presenter: TestFragmentPresenter) {
        mPresenter = presenter
    }

    override fun onInitViews() {

    }

    override fun onInitData() {
        mPresenter?.loadData()
    }

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_test, null)
    }

}