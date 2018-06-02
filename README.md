# 简介
该项目是基于MVP封装的页面基类，包含了页面的状态管理。使用kotlin编写。

# 使用
```gradle
dependencies {
    compile 'cn.jiajunhui:mvp:0.0.2'
}
```

# 示例

Activity

```kotlin
class MainActivity : BaseAppActivity<MainPresenter, IMainPresenter.IMainView>(),IMainPresenter.IMainView {

    private var mPresenter:MainPresenter?=null
    //...
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

    override fun onSuccess() {
        switchScreenFillState(this, true, false)
    }

    override fun onErrorRetry() {
        super.onErrorRetry()
        mPresenter?.loadData()
    }

    override fun onCreateContentView(): View {
        return LayoutInflater.from(this).inflate(R.layout.activity_main, null)
    }
    //...
}
```

Fragment

```kotlin
class TestFragment : BaseAppFragment<TestFragmentPresenter, ITestFragmentPresenter.ITestFragmentView>()
        , ITestFragmentPresenter.ITestFragmentView{

    private var mPresenter:TestFragmentPresenter?=null
    //...
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
    //...
}
```