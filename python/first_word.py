import re

# Test output
command_output = """
21     Ab0               Ab3               XYZ
22     Ab0               Ab3               XYZ
"""

# extract words
words = re.findall(r'(\w+)', command_output)

# first word. Output will be : "21"
print("First word in command output is : " + words[0])
