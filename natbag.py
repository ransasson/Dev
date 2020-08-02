
import subprocess

from flask import Flask, request

app = Flask("natbag2020_app")

def flights(direction):
    return subprocess.check_output(["java", "-classpath",
                                    "bin", "core.Program",
                                    request.args.get('outformat'), direction, request.args.get('country'),
                                    request.args.get('city'),request.args.get('airport'),request.args.get('airline'),
                                    request.args.get('day1'), request.args.get('month1'),request.args.get('year1'),
                                    request.args.get('day2'),request.args.get('month2'),request.args.get('year2'),
                                    request.args.get('sunday'), request.args.get('monday'),
                                    request.args.get('tuesday'), request.args.get('wednesday'),
                                    request.args.get('thursday'), request.args.get('friday'),
                                    request.args.get('saturday')])

@app.route("/departures")
def dep():
    return flights("departures")

@app.route("/arrivals")
def arr():
    return flights("arrivals")

app.run(port=8000, host="0.0.0.0")