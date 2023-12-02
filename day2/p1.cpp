#include <iostream>
#include <fstream>
using namespace std;


void line_possible(bool* possible, string line) {
    int lim_red = 12, lim_green = 13, lim_blue = 14;
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
            if (red > lim_red || green > lim_green || blue > lim_blue) {
                *possible = false;
                break;
            } else *possible = true;

            red = 0;
            green = 0;
            blue = 0;
            i += 1;
        }
    }
}

void solve_p1(int* total, string input_data_path) {
    fstream file;
    string line;
    file.open(input_data_path);

    if (file.is_open()) {
        int i = 1;
        while (getline(file, line)) {
            bool possible;
            line_possible(&possible, line);
            if (possible) *total += i;
            i++;
        }
    } else {
        throw invalid_argument("ERROR: Input file could not be opened");
    }
}