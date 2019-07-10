package com.afcodingtest.koti.ui.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.afcodingtest.koti.AFApplication
import com.afcodingtest.koti.R
import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.di.component.DaggerAppComponent
import com.afcodingtest.koti.di.module.PromotionsPresenterModule
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.ui.adapter.PromotionsAdapter
import kotlinx.android.synthetic.main.activity_promotions.*
import javax.inject.Inject

class PromotionsActivity : AppCompatActivity(), PromotionsContract.View, PromotionsAdapter.ContentClickListener {

    @Inject
    lateinit var presenter: PromotionsContract.Presenter
    private var adapter: PromotionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotions)
        (application as AFApplication).appComponent.inject(this)
        title = getString(R.string.title_promotions)
        presenter.attachView(this)
        presenter.loadPromotions()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showPromotions(promotions: ArrayList<Promotion>) {
        adapter = PromotionsAdapter(promotions)
        item_list.adapter = adapter
        adapter?.setOnContentClickListener(this)
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        if (isActive)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.INVISIBLE
    }

    override fun showLoadingError(error: String) {
        Snackbar.make(container, error, Snackbar.LENGTH_LONG).show()
        adapter?.clear()
    }

    override fun onContentClick(target: String?) {
        presenter.showDetail(target)
    }

    override fun showDetail(target: String) {
        Intent(this, WebActivity::class.java).apply {
            putExtra(getString(R.string.extra_target), target)
            startActivity(this)
        }
    }
}
