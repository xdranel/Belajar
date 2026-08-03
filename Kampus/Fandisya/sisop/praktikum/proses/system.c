#include <stdlib.h>

int main() {
  int return_value;
  return_value = system("python3 ../teori/race/raceCondition_NoMutex.py");
  return return_value;
}
