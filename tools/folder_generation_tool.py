import csv
from dataclasses import dataclass
import os
from pathlib import Path
from typing import Optional


@dataclass
class Participant:
    """Data class to represent a participant."""
    name: str
    language: str


LANGUAGE_TO_EXT = {
    'python': 'py',
    'java': 'java',
    'cpp': 'cpp',
    'kotlin': 'kt',
    'csharp': 'cs',
    'javascript': 'js',
    'typescript': 'ts',
    'go': 'go',
    'lua': 'lua',
    # Add more languages and their extensions as needed
}


def get_most_recent_week(directory: os.PathLike) -> Optional[int]:
    """Read the folders in `directory`, and determine the most recent week."""
    # List all directories in the specified directory
    try:
        folders = [f for f in os.listdir(directory) if os.path.isdir(
            os.path.join(directory, f))]
    except FileNotFoundError:
        print(f"Error: The directory {directory} does not exist.")
        return None

    # Filter folders that match the week format (e.g., 'Week_1', 'Week_2', etc.)
    week_folders = [f for f in folders if f.startswith('Week_')]

    if not week_folders:
        print("No week folders found.")
        return None

    # Extract week numbers and find the maximum
    most_recent_week = 0
    for folder in week_folders:
        try:
            week_number = int(folder.split('_')[1])
            most_recent_week = max(most_recent_week, week_number)
        except (IndexError, ValueError):
            print(f"Skipping invalid folder name: {folder}")

    if most_recent_week == 0:
        print("No valid week numbers found.")
        return None

    return most_recent_week


def create_next_week_folder(directory: os.PathLike):
    """Create a new folder for the next week based on the most recent week."""
    most_recent_week = get_most_recent_week(directory)

    if most_recent_week is None:
        return

    next_week_number = most_recent_week + 1
    new_folder_name = f"Week_{next_week_number}"
    new_folder_path = os.path.join(directory, new_folder_name)

    try:
        os.makedirs(new_folder_path)
        print(f"Created new folder: {new_folder_name}")
        create_day_folders(new_folder_path)
        print(f"Created day folders in: {new_folder_name}")
    except FileExistsError:
        print(f"Folder {new_folder_name} already exists.")
    except Exception as e:
        print(f"An error occurred while creating the folder: {e}")


def create_day_folders(week_dir: os.PathLike, days=5):
    """Create folders for each day of the week in the specified week directory."""
    for day in range(1, days + 1):
        day_folder_name = f"Day_{day}"
        day_folder_path = os.path.join(week_dir, day_folder_name)
        try:
            os.makedirs(day_folder_path)
            create_solutions_folder(day_folder_path)
            print(f"Created folder: {day_folder_name}")
        except FileExistsError:
            print(f"Folder {day_folder_name} already exists.")
        except Exception as e:
            print(f"An error occurred while creating the folder: {e}")


def create_solutions_folder(day_dir: os.PathLike):
    """Create a 'solutions' folder inside the specified day directory."""
    solutions_folder_name = "solutions"
    solutions_folder_path = os.path.join(day_dir, solutions_folder_name)
    try:
        os.makedirs(solutions_folder_path)
        print(f"Created folder: {solutions_folder_name} in {day_dir}")
        participants = read_participants_from_csv(
            os.path.join(os.path.dirname(__file__), 'participants.csv')
        )
        for participant in participants:
            create_language_folder_with_file(
                solutions_folder_path, participant)
    except FileExistsError:
        print(f"Folder {solutions_folder_name} already exists in {day_dir}.")
    except Exception as e:
        print(f"An error occurred while creating the folder: {e}")


def create_language_folder_with_file(day_dir: os.PathLike, participant: Participant):
    """Create a language folder with a solution file for the participant."""
    language_folder_name = participant.language
    language_folder_path = os.path.join(day_dir, language_folder_name)

    try:
        os.makedirs(language_folder_path, exist_ok=True)
        # Create the solution file
        print(f"Created folder: {language_folder_name} in {day_dir}")
        create_solution_file(language_folder_path, participant)
    except FileExistsError:
        print(f"Folder {language_folder_name} already exists in {day_dir}.")
    except Exception as e:
        print(f"An error occurred while creating the folder: {e}")


def read_participants_from_csv(file_path: os.PathLike) -> Optional[list[Participant]]:
    """Read the participants from a file and return them as a list."""
    try:
        with open(file_path, 'r') as f:
            reader = csv.DictReader(f)
            participants = [
                Participant(
                    name=row['name'],
                    language=row['language'],
                )
                for row in reader if row['name'] and row['language']
            ]
            return participants
    except FileNotFoundError:
        print(f"Error: The file {file_path} does not exist.")
        return None
    except Exception as e:
        print(f"An error occurred while reading the file: {e}")
        return None


def format_comments(delimiter: str, *comments: str) -> str:
    """Format multiple lines with the appropriate comment delimiter."""
    # Can be modified later to write out multi-line comments with specific opening and closing delimiters.
    return "\n".join(f"{delimiter} {comment}" for comment in comments)


def create_solution_file(language_dir: os.PathLike, participant: Participant):
    """Create a solution file in the specified language directory."""
    if participant is None:
        print("No participant data provided.")
        return

    ext = LANGUAGE_TO_EXT.get(participant.language.lower())
    if ext is None:
        print(f"Unsupported language: {participant.language}")
        return

    comment_delimiter = ""
    match participant.language.lower():
        case 'python':
            comment_delimiter = "#"
        case 'java' | "cpp" | "kotlin" | "csharp" | "javascript" | "go":
            comment_delimiter = "//"
        case "lua":
            comment_delimiter = "--"
        case _:
            print(f"Unsupported language: {participant.language}")
            return

    comment = format_comments(
        comment_delimiter,
        "Generated solution file (from the 52 Weeks of DSA folder generation tool).",
        "----------------------------",
        "Fill in your solution here.",
        "Don't forget to add runtime and space complexity analysis.",
        "You can also add (as needed) any additional comments on the specifics behind how your solution works.",
        "----------------------------",
    )

    filename = f"{participant.name}_solution.{ext}"
    solution_file_path = os.path.join(language_dir, filename)

    try:
        with open(solution_file_path, 'w') as f:
            f.write(comment)
        print(f"Created solution file: {solution_file_path}")
    except Exception as e:
        print(f"An error occurred while creating the solution file: {e}")


def main():
    parent_directory = Path(os.path.dirname(__file__)).resolve().parent
    print(f"Parent directory: {parent_directory}")
    create_next_week_folder(parent_directory)


if __name__ == "__main__":
    # This can be changed later to accept command line arguments, and
    # show usage instructions and other quality of life features.
    # For now, it will just create the next week folder in the parent directory.
    main()
