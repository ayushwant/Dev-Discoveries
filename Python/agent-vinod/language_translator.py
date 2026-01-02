from deep_translator import GoogleTranslator
from flask import Flask, request, jsonify
from flask_cors import CORS
from deep_translator import GoogleTranslator

app = Flask(__name__)
CORS(app)

langs_list = GoogleTranslator().get_supported_languages()
print("List of Languages",langs_list)
print("Total Languages processes", len(langs_list))
langs_dict = GoogleTranslator().get_supported_languages(as_dict=True)
print("List of language codes",langs_dict)

if __name__ == "__main__":
    app.run(debug=True)



    # AIzaSyBhuwtfkGUuxRslk5IgV5kHrsrXQFW2JPY