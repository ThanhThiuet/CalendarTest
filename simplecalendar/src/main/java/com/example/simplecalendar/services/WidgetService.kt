package com.example.simplecalendar.services

import android.content.Intent
import android.widget.RemoteViewsService
import com.example.simplecalendar.adapters.EventListWidgetAdapter

class WidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent) = EventListWidgetAdapter(applicationContext)
}
