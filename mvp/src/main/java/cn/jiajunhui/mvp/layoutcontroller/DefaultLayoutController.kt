package cn.jiajunhui.mvp.layoutcontroller

import android.content.Context
import android.os.Bundle

class DefaultLayoutController(context: Context) : BaseLayoutController(context) {

    override fun onLayoutPartAttached() {
        success(null)
    }

    override fun loading(bundle: Bundle?) {
        val loadingView = getLayoutPart()?.getLoadingView()
        if(loadingView!=null){
            loadingView.setOnClickListener {  }
            removeAllViews()
            addView(loadingView,
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        }
    }

    override fun success(bundle: Bundle?) {
        val userContentView = getLayoutPart()?.getUserContentView()
        if(userContentView!=null){
            removeAllViews()
            addView(userContentView,
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        }
    }

    override fun error(bundle: Bundle?) {
        val errorView = getLayoutPart()?.getErrorView()
        if(errorView!=null){
            errorView.setOnClickListener {
                sendEvent(LAYOUT_EVENT_CODE_ERROR_RETRY, null)
            }
            removeAllViews()
            addView(errorView,
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        }
    }

    override fun extra(type: Int, bundle: Bundle?) {
        val extraView = getLayoutPart()?.getExtraView(type)
        if(extraView!=null){
            removeAllViews()
            addView(extraView,
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        }
    }

}