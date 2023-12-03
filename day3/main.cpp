#include <iostream>
#include <fstream>
using namespace std;


void symbol_line(bool* is_adjacent, int i, string line, bool is_cur_line = false) {
    for (int j = 0; j < 3; j++) {
        int idx = i-1+j;
        if (idx < 0) continue;
        else if (idx > line.size()-1) continue;
        else if (idx == i && is_cur_line) continue;

        *is_adjacent = *is_adjacent || (!isdigit(line[idx]) && line[idx] != '.');
    }
}

void symbol_adjacent(bool* is_adjacent, int i, string line, string last_line, string next_line) {
    if (last_line != "") {
        symbol_line(is_adjacent, i, last_line);
    } 
    if (next_line != "") {
        symbol_line(is_adjacent, i, next_line);
    }
    symbol_line(is_adjacent, i, line, true);
}

void compute_line(int* total, string line, string last_line, string next_line) {
    string buffer = "";
    bool is_adjacent = false;
    for (int i = 0; i < line.length(); i++) {
        if (isdigit(line[i])) {
            buffer += line[i];
            symbol_adjacent(&is_adjacent, i, line, last_line, next_line);
        }
        if ((!isdigit(line[i]) && buffer != "") || (i == line.size()-1 && buffer != "")) {
            if (is_adjacent) *total += stoi(buffer);

            buffer = "";
            is_adjacent = false;
        }
    }
}

void solve(int* total, string input_data_path) {
    fstream file;
    string line, next_line, last_line;
    file.open(input_data_path);

    if (file.is_open()) {
        while (getline(file, next_line) || line != "") {
            if (line != "") compute_line(total, line, last_line, next_line);
            
            last_line = line;
            line = next_line;
        }
    }
}

int main() {
    string input_data_path = "input";
    int total = 0;

    solve(&total, input_data_path);

    cout << total << endl;

    return 0;
}
