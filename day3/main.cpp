#include <iostream>
#include <fstream>
using namespace std;


void compute_line_p1(int* total, string line, string last_line, string next_line);
void compute_line_p2(int* total, string line, string last_line, string next_line);


void solve(int* total, string input_data_path) {
    fstream file;
    string line, next_line, last_line;
    file.open(input_data_path);

    if (file.is_open()) {
        while (getline(file, next_line) || line != "") {
            if (line != "") compute_line_p1(total, line, last_line, next_line);
            
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
