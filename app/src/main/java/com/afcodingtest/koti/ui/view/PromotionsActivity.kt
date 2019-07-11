package com.afcodingtest.koti.ui.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.afcodingtest.koti.AFApplication
import com.afcodingtest.koti.R
import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.ui.BaseActivity
import com.afcodingtest.koti.ui.adapter.PromotionsAdapter
import kotlinx.android.synthetic.main.activity_promotions.*
import javax.inject.Inject

class PromotionsActivity : BaseActivity(), PromotionsContract.View {

    @Inject
    lateinit var presenter: PromotionsContract.Presenter
    private var adapter: PromotionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotions)
        (application as AFApplication).appComponent.inject(this)
        title = getString(R.string.title_promotions)
        initialize()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    private fun initialize() {
        presenter.attachView(this)
        presenter.loadPromotions()
        adapter = PromotionsAdapter {
            target -> showDetail(target =  target)
        }
        item_list.adapter = adapter
    }

    override fun showPromotions(promotions: List<Promotion>) {
        adapter?.setData(promotions)
        adapter?.notifyDataSetChanged()
    }

    override fun showLoadingError(error: String) {
        super.showLoadingError(error)
        adapter?.clear()
    }

    override fun showDetail(target: String) {
        Intent(this, WebActivity::class.java).apply {
            putExtra(getString(R.string.extra_target), target)
            startActivity(this)
        }
    }
}
