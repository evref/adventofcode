#include <iostream>
#include <fstream>
using namespace std;


void solve_p1(int* total, string input_data_path);
void solve_p2(int* total, string input_data_path);


int main() {
    string input_data_path = "input";
    int total = 0;
    solve_p2(&total, input_data_path);

    cout << to_string(total) << endl;

    return 0;
}