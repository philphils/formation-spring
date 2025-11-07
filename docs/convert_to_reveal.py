import sys

def convert_md_to_reveal(input_file, output_file):
    with open(input_file, "r", encoding="utf-8") as f:
        lines = f.readlines()

    result = []
    for line in lines:
        if line.startswith("# "):  # chaque titre de niveau 1 => nouvelle diapo
            if result and not result[-1].strip().endswith("---"):
                result.append("\n---\n")
        result.append(line)

    with open(output_file, "w", encoding="utf-8") as f:
        f.writelines(result)

    print(f"Conversion terminée ✅ : {output_file}")

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python convert_to_reveal.py input.md output.md")
    else:
        convert_md_to_reveal(sys.argv[1], sys.argv[2])