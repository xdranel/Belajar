filenames = ["app.go", "main.go", "formula.go", "program.go"]
new_filenames = []

for filename in filenames:
    new_name = filename.replace(".go", ".py")
    new_filenames.append(new_name)
print(new_filenames)
