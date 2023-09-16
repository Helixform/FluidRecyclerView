/*
 * Copyright (C) 2023 Helixform
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.helixform.fluidrecyclerview.example

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fluidrecyclerview.widget.RecyclerView
import androidx.fluidrecyclerview.widget.RecyclerView.Adapter
import androidx.fluidrecyclerview.widget.RecyclerView.ViewHolder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        }

        window.navigationBarColor = Color.TRANSPARENT

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val recyclerView: RecyclerView = findViewById(R.id.rv)
        recyclerView.adapter = object : Adapter<SimpleViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
                val view = layoutInflater.inflate(R.layout.item_text, parent, false)
                return SimpleViewHolder(view)
            }

            override fun getItemCount(): Int {
                return 50
            }

            @SuppressLint("SetTextI18n")
            override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
                holder.textView.text = "Item ${position + 1}"
            }
        }
        val recyclerViewDefaultBottomPadding = recyclerView.paddingBottom
        ViewCompat.setOnApplyWindowInsetsListener(recyclerView) { v, insets ->
            val bottomInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
            v.updatePadding(bottom = (recyclerViewDefaultBottomPadding + bottomInsets.bottom))
            WindowInsetsCompat.CONSUMED
        }
    }

    private class SimpleViewHolder(itemView: View) : ViewHolder(itemView) {
        val textView: TextView

        init {
            textView = itemView.findViewById(R.id.text)

            itemView.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    if (view == null || outline == null) {
                        return
                    }

                    val radius = 16f
                    val radiusPx = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, radius, view.resources.displayMetrics)
                    outline.setRoundRect(0, 0, view.width, view.height, radiusPx)
                }
            }
            itemView.clipToOutline = true
        }
    }
}