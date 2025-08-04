import os
from pathlib import Path
from typing import Optional


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
            print(f"Created folder: {day_folder_name}")
        except FileExistsError:
            print(f"Folder {day_folder_name} already exists.")
        except Exception as e:
            print(f"An error occurred while creating the folder: {e}")


def main():
    parent_directory = Path(os.path.curdir).resolve().parent
    print(f"Parent directory: {parent_directory}")
    create_next_week_folder(parent_directory)


if __name__ == "__main__":
    main()
