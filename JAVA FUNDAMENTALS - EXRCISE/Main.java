package java_fundamentals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class InvalidSongException extends IllegalArgumentException {

        @Override
        public String getMessage() {
            return "Invalid Song Exception";
        }
    }

    private static class InvalidArtistNameException extends InvalidSongException {

        @Override
        public String getMessage() {
            return "Artist name should be between 3 and 20 symbols.";
        }
    }

    private static class InvalidSongNameException extends InvalidSongException {

        @Override
        public String getMessage() {
            return "Song name should be between 3 and 30 symbols.";
        }
    }

    private static class InvalidSongLengthException extends InvalidSongException {

        @Override
        public String getMessage() {
            return "Invalid song length.";
        }
    }

    private static class InvalidSongMinutesException extends InvalidSongLengthException {

        @Override
        public String getMessage() {
            return "Song minutes should be between 0 and 14.";
        }
    }

    private static class InvalidSongSecondsException extends InvalidSongLengthException {

        @Override
        public String getMessage() {
            return "Song seconds should be between 0 and 59.";
        }
    }

    private static class Song {
        private String artistName;
        private String songName;
        private long minutes;
        private long seconds;

        public Song(String artistName, String songName, long minutes, long seconds) {
            this.setArtistName(artistName);
            this.setSongName(songName);
            this.setMinutes(minutes);
            this.setSeconds(seconds);
            this.isSongLengthTooBig();
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            if (artistName.length() < 3 || artistName.length() > 20) {
                throw new InvalidArtistNameException();
            }
            this.artistName = artistName;
        }

        public String getSongName() {
            return songName;
        }

        public void setSongName(String songName) {
            if (songName.length() < 3 || songName.length() > 30) {
                throw new InvalidSongNameException();
            }
            this.songName = songName;
        }

        public long getMinutes() {
            return minutes;
        }

        public void setMinutes(long minutes) {
            if (minutes < 0 || minutes > 14) {
                throw new InvalidSongMinutesException();
            }
            this.minutes = minutes;
            this.isSongLengthTooBig();
        }

        public long getSeconds() {
            return seconds;
        }

        public void setSeconds(long seconds) {
            if (seconds < 0 || seconds > 59) {
                throw new InvalidSongSecondsException();
            }
            this.seconds = seconds;
            this.isSongLengthTooBig();
        }

        private void isSongLengthTooBig() {
            long fullTime = this.minutes * 60 + this.seconds;
            long maxTime = 14 * 60 + 59;
            if (fullTime > maxTime) {
                throw new InvalidSongLengthException();
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int countOfSongs = Integer.parseInt(input.nextLine());
        Song[] songs = new Song[countOfSongs];
        for (int i = 0; i < songs.length; i++) {
            try {
                String[] infoAboutSong = input.nextLine().split(" *;+ *");
                String artistName = infoAboutSong[0];
                String songName = infoAboutSong[1];
                long minutes = Long.parseLong(infoAboutSong[2].split(" *:+ *")[0]);
                long seconds = Long.parseLong(infoAboutSong[2].split(" *:+ *")[1]);

                Song song = new Song(artistName, songName, minutes, seconds);
                songs[i] = song;
                System.out.println("Song added.");
            } catch (InvalidSongException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid song length.");
            }
        }
        System.out.println("Songs added: " + calculateAddedSongs(songs));
        System.out.println(totalLength(songs));
    }

    private static String totalLength(Song[] songs) {
        long totalSeconds = 0;
        for (Song song : songs) {
            if (song != null) {
                totalSeconds += song.getSeconds() + song.getMinutes() * 60;
            }
        }
        return String.format("Playlist length: %dh %dm %ds", totalSeconds / 3600, totalSeconds / 60 % 60,
                totalSeconds % 60);
    }

    private static int calculateAddedSongs(Song[] songs) {
        int count = 0;
        for (Song song : songs) {
            if (song != null) {
                count++;
            }
        }

        return count;
    }
}
