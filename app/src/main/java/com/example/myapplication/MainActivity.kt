package com.example.myapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var rotationVectorSensor: Sensor? = null
    private lateinit var compassContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compassContainer = findViewById(R.id.compass_container)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
    }

    override fun onResume() {
        super.onResume()
        rotationVectorSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ROTATION_VECTOR) {
            val rotationMatrix = FloatArray(9)
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            val orientationAngles = FloatArray(3)
            SensorManager.getOrientation(rotationMatrix, orientationAngles)
            val azimuth = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()
            Log.d("CompassSensor", "Sensor event: ${event.values.contentToString()}, calculated azimuth: $azimuth")
            compassContainer.rotation = -azimuth
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No action required
    }
}