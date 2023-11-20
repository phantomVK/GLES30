package com.phantomvk.gles30.app

import android.opengl.GLES30
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class PlatformRender : GLSurfaceView.Renderer {

  private val vertexShader = """
      attribute vec4 vPosition;
      void main() {
        gl_Position = vPosition;
      }
    """.trimIndent()

  private val fragmentShader = """
      precision mediump float;
      void main() {
        gl_FragColor = vec4(1, 1, 1, 1);
      }
    """.trimIndent()

  private val vertex = floatArrayOf(
    0f, 1f, 0f,
    -1f, -1f, 0f,
    1f, -1f, 0f
  )

  private val vertexBuffer = ByteBuffer
    .allocateDirect(vertex.size * 4) // Float需要4bytes内存
    .order(ByteOrder.nativeOrder())
    .asFloatBuffer()
    .put(vertex)
    .also { it.position(0) }

  private fun loadShader(type: Int, shaderCode: String): Int {
    val shader = GLES30.glCreateShader(type)
    GLES30.glShaderSource(shader, shaderCode)
    GLES30.glCompileShader(shader)
    return shader
  }

  override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
    // 背景颜色为红色
    GLES30.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)

    val program = GLES30.glCreateProgram()

    // Load vertex shader.
    val vertexShader = loadShader(GLES30.GL_VERTEX_SHADER, vertexShader)
    GLES30.glAttachShader(program, vertexShader)

    // Load fragment shader.
    val fragmentShader = loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentShader)
    GLES30.glAttachShader(program, fragmentShader)

    GLES30.glLinkProgram(program)
    GLES30.glUseProgram(program)

    val position = GLES30.glGetAttribLocation(program, "vPosition")
    GLES30.glEnableVertexAttribArray(position)
    GLES30.glVertexAttribPointer(position, 3, GLES30.GL_FLOAT, false, 12, vertexBuffer)
  }

  override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
    GLES30.glViewport(0, 0, width, height)
  }

  override fun onDrawFrame(gl: GL10) {
    GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)
    GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3)
  }
}