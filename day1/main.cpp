#include <iostream>
#include <fstream>
#include <list>
using namespace std;


void p1_extract_value(int* value, string line);
void p2_extract_value(int* value, string line);


void solve(int* total, string input_data_path) {
    ifstream file;
    string line;
    file.open(input_data_path);

    if (file.is_open()) {
        while (getline(file, line)) {
            int value;
            p2_extract_value(&value, line);
            *total += value;
        }
    } else {
        throw invalid_argument("ERROR: The file could not be opened");
    }
    
    file.close();
}

int main() {
    string input_data_path = "input";
    int total = 0;
    solve(&total, input_data_path);
    cout << total << endl;

    return 0;
}
