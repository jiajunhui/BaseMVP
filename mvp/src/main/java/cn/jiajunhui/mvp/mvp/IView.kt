package cn.jiajunhui.mvp.mvp

import android.os.Bundle

interface IView {

    fun stateLoading(bundle: Bundle?)

    fun stateSuccess(bundle: Bundle?)

    fun stateError(bundle: Bundle?)

    fun stateExtra(type: Int, bundle: Bundle?)

}