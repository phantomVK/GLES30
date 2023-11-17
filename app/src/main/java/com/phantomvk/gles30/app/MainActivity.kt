package com.phantomvk.gles30.app

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  private lateinit var glSurfaceView: GLSurfaceView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    glSurfaceView = makeGLSurfaceView()
    setContentView(glSurfaceView)
  }

  private fun makeGLSurfaceView(): GLSurfaceView {
    return GLSurfaceView(this).apply {
      setEGLContextClientVersion(3)
      setRenderer(PlatformRender())
      renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
  }

  override fun onResume() {
    super.onResume()
    glSurfaceView.onResume()
  }

  override fun onPause() {
    super.onPause()
    glSurfaceView.onPause()
  }
}