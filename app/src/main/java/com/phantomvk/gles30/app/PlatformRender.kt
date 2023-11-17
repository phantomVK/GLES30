package com.phantomvk.gles30.app

import android.opengl.GLES30
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class PlatformRender : GLSurfaceView.Renderer {
  override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
    GLES30.glClearColor(1.0F, 0.0F, 0.0F, 1.0F)
  }

  override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
    GLES30.glViewport(0, 0, width, height)
  }

  override fun onDrawFrame(gl: GL10?) {
    GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)
  }
}