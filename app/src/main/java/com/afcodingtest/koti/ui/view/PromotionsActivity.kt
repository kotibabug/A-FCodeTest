package com.afcodingtest.koti.ui.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import com.afcodingtest.koti.R
import com.afcodingtest.koti.contract.PromotionsContract
import com.afcodingtest.koti.model.Promotion
import com.afcodingtest.koti.presenter.PromotionsPresenter
import com.afcodingtest.koti.ui.adapter.PromotionsAdapter
import kotlinx.android.synthetic.main.activity_promotions.*

class PromotionsActivity : AppCompatActivity(), PromotionsContract.View, PromotionsAdapter.ContentClickListener {

    private lateinit var presenter: PromotionsContract.Presenter
    private var adapter: PromotionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotions)
        title = getString(R.string.title_promotions)
        PromotionsPresenter(this)
        presenter.loadPromotions()
    }

    override fun showPromotions(promotions: ArrayList<Promotion>) {
        adapter = PromotionsAdapter(promotions)
        item_list.adapter = adapter
        adapter?.setOnContentClickListener(this)
    }

    override fun setPresenter(presenter: PromotionsContract.Presenter) {
        this.presenter = presenter
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

    override fun onContentClick(target: String) {
       presenter.showDetail(target)
    }

    override fun showDetail(target: String) {
        Intent(this, WebActivity::class.java).apply {
            putExtra(getString(R.string.extra_target), target)
            startActivity(this)
        }
    }
}
