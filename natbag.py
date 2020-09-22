import subprocess

from flask import Flask, request

app = Flask("natbag2020_app")


def flights_curl(direction):
    return subprocess.check_output(["java", "-classpath",
                                    "bin", "core.Program",
                                    request.args.get('outformat'), direction, request.args.get('country'),
                                    request.args.get('city'), request.args.get('airport'), request.args.get('airline'),
                                    request.args.get('day1'), request.args.get('month1'), request.args.get('year1'),
                                    request.args.get('day2'), request.args.get('month2'), request.args.get('year2'),
                                    request.args.get('sunday'), request.args.get('monday'),
                                    request.args.get('tuesday'), request.args.get('wednesday'),
                                    request.args.get('thursday'), request.args.get('friday'),
                                    request.args.get('saturday')])


def flights_html(direction, country, city, airport, airline, day1, month1, year1, day2, month2, year2, sunday,
                 monday, tuesday, wednesday, thursday, friday, saturday):
    return subprocess.check_output(["java", "-classpath",
                                    "bin", "core.Program", "html", direction, country, city, airport, airline, day1,
                                    month1, year1, day2, month2, year2, sunday, monday, tuesday, wednesday, thursday,
                                    friday, saturday])


@app.route("/departures")
def dep():
    return flights_curl("departures")


@app.route("/arrivals")
def arr():
    return flights_curl("arrivals")


@app.route('/Natbag', methods=['GET', 'POST'])  # allow both GET and POST requests
def get_data():
    if request.method == 'POST':  # this block is only entered when the form is submitted
        direction = request.form.get('direction')
        country = request.form.get('country')
        city = request.form.get('city')
        airport = request.form.get('airport')
        airline = request.form.get('airline')
        day1 = request.form.get('day1')
        month1 = request.form.get('month1')
        year1 = request.form.get('year1')
        day2 = request.form.get('day2')
        month2 = request.form.get('month2')
        year2 = request.form.get('year2')
        sunday = request.form.get('sunday')
        monday = request.form.get('monday')
        tuesday = request.form.get('tuesday')
        wednesday = request.form.get('wednesday')
        thursday = request.form.get('thursday')
        friday = request.form.get('friday')
        saturday = request.form.get('saturday')
        return flights_html(direction, country, city, airport, airline, day1, month1, year1, day2, month2, year2,
                            sunday, monday, tuesday, wednesday, thursday, friday, saturday)

    return '''<form method="POST">
                  <h1>Please fill all the fields you would like to search by:</h1><br>  
                  Direction: <input type="text" name="direction"><br>
                  Country: <input type="text" name="country"><br>
                  City: <input type="text" name="city"><br>
                  Airport: <input type="text" name="airport"><br>
                  Airline: <input type="text" name="airline"><br>
                  Day1: <input type="text" name="day1"><br>
                  Month1: <input type="text" name="month1"><br>
                  Year1: <input type="text" name="year1"><br>
                  Day2: <input type="text" name="day2"><br>
                  Month2: <input type="text" name="month2"><br>
                  Year2: <input type="text" name="year2"><br>
                  Sunday: <input type="text" name="sunday"><br>
                  Monday: <input type="text" name="monday"><br>
                  Tuesday: <input type="text" name="tuesday"><br>
                  Wednesday: <input type="text" name="wednesday"><br>
                  Thursday: <input type="text" name="thursday"><br>
                  Friday: <input type="text" name="friday"><br>
                  Saturday: <input type="text" name="saturday"><br>

                  <input type="submit" value="Search"><br>
              </form>'''


app.run(port=8000, host="0.0.0.0")
