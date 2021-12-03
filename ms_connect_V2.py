"""
This file posts to the local python server.
In order for the microservice functionality to work,
    this server must be active before moving on to the "view results" page.
If it doesn't run, it may be running on an incorrect port.
When I test it, it is on 0.0.0.0:5000

Entry is 2 lists, r, and line1.
List1 is x axis points.
List2 is y axis points.

To any graders. The first half of the path (line 31) will likely need to be
    changed on your system in order to use this correctly.
"""

import os
import requests
from requests.structures import CaseInsensitiveDict
from flask import Flask
from flask_restful import Resource, Api

app = Flask(__name__)
api = Api(app)


class JsonLinker(Resource):
    def get(self, data):
        if data == "favicon.ico":
            print("Doing Nothing...")
        else:
            path = "D:\\Programming\\Oregon State Files\\361 - Software Engineering 1\\SE_Project\\V2\\app\\" \
                   "src\\main\\res\\drawable"
            locked = """
            {
            "dpi": 300,
            "format": "line",
            "output": "PNG",
            "filename": "graph",
            "title": "Exercise Results",
            "xlabel": "Exercise Count",
            "ylabel": "Total Reps",
            "legend": false,
            "grid": true,
            """
            temp_data = str(data)
            url = "https://graph-microservice.herokuapp.com/"
            headers = CaseInsensitiveDict()
            headers["Content-Type"] = "application/json"
            r = requests.post(url, headers=headers, data=locked+temp_data+"}")
            name = os.path.join(path, "graph.png") #name = "img.png"
            with open(name, 'wb') as f:
                for chunk in r.iter_content():
                    f.write(chunk)
        return data


api.add_resource(JsonLinker, "/<string:data>")

if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=5000)
