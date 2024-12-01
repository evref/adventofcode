#include <iostream>
#include <fstream>
using namespace std;


void calc_power_of_set(int* power_of_set, string line) {
    int max_red = 0, max_green = 0, max_blue = 0;
    int red = 0, green = 0, blue = 0;

    bool last_num = false;
    int temp_num = 0;
    string buffer = "";
    
    // Start at i=8 to ignore "Game X: "
    for (int i = 8; i < line.length()+1; i++) {
        if (isdigit(line[i])) {
            last_num = true;
            buffer += line[i];
        } else if (!isdigit(line[i]) && last_num) {
            last_num = false;
            temp_num = stoi(buffer);
            buffer = "";
        } else if (line[i] == 'r') {
            red = temp_num;
            temp_num = 0;
            i += 2;
        } else if (line[i] == 'g') {
            green = temp_num;
            temp_num = 0;
            i += 4;
        } else if (line[i] == 'b') {
            blue = temp_num;
            temp_num = 0;
            i += 3;
        } else if (line[i] == ';' || i == line.size()) {
            if (red > max_red) max_red = red;
            if (green > max_green) max_green = green;
            if (blue > max_blue) max_blue = blue;

            red = 0;
            green = 0;
            blue = 0;
            i += 1;
        }
    }

    *power_of_set = max_red * max_green * max_blue;
}

void solve_p2(int* total, string input_data_path) {
    fstream file;
    string line;
    file.open(input_data_path);

    if (file.is_open()) {
        while (getline(file, line)) {
            bool possible;
            int power_of_set;
            calc_power_of_set(&power_of_set, line);
            *total += power_of_set;
        }
    } else {
        throw invalid_argument("ERROR: Input file could not be opened");
    }
}