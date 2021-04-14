#include "Arduino.h"
#include <Wire.h>
#include "MLX90640_API.h"
#include "MLX90640_I2C_Driver.h"

#define EMMISIVITY 0.95
#define TA_SHIFT 8 

paramsMLX90640 mlx90640;
const byte MLX90640_address = 0x33; //Default 7-bit unshifted address of the MLX90640
static float tempValues[32 * 24];

void setup() {
  Serial.begin(115200);
  Wire.begin();
  Wire.setClock(400000); // 400000
  Wire.beginTransmission((uint8_t)MLX90640_address);
  if (Wire.endTransmission() != 0) {
    Device_Scan();
  }
  int status;
  MLX90640_I2CInit();
  uint16_t eeMLX90640[832];
  status = MLX90640_DumpEE(MLX90640_address, eeMLX90640);
  status = MLX90640_ExtractParameters(eeMLX90640, &mlx90640);
  Wire.setClock(800000); // 800000
}

void loop(void) {
  readTempValues();
}

void readTempValues() {
  for (byte x = 0 ; x < 2 ; x++) 
  {
    uint16_t mlx90640Frame[834];
    int status = MLX90640_GetFrameData(MLX90640_address, mlx90640Frame);
    float vdd = MLX90640_GetVdd(mlx90640Frame, &mlx90640);
    float Ta = MLX90640_GetTa(mlx90640Frame, &mlx90640);

    float tr = Ta - TA_SHIFT; 

    MLX90640_CalculateTo(mlx90640Frame, &mlx90640, EMMISIVITY, tr, tempValues);
  }
//  Serial.println("\r\n===========================WaveShare MLX90640 Thermal Camera===============================");
  Serial.print("#");
  for (int i = 0; i < 768; i++) {
    Serial.print((int)tempValues[i]);
    Serial.print(" ");
  }
  Serial.print("!");
//  Serial.println("\r\n===========================WaveShare MLX90640 Thermal Camera===============================");
}

void Device_Scan() {
  byte error, address;
  int nDevices;
  Serial.println("Scanning...");
  nDevices = 0;
  for (address = 1; address < 127; address++ )
  {
    Wire.beginTransmission(address);
    error = Wire.endTransmission();
    if (error == 0)
    {
      if (address < 16)
      nDevices++;
    }
  }
}
