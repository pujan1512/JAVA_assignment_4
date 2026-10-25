import java.util.*;

class Song {
    String id;
    String title;

    Song(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String toString() {
        return title;
    }
}

class MediaPlayer {
    LinkedList<Song> playlist = new LinkedList<>();
    ArrayList<Song> history = new ArrayList<>();
    HashSet<String> songIds = new HashSet<>();

    void addSong(Song s) {
        if (songIds.add(s.id)) {
            playlist.addLast(s);
            System.out.println(s.title + " added");
        } else {
            System.out.println("Duplicate song");
        }
    }

    void addPriority(Song s) {
        if (songIds.add(s.id)) {
            playlist.addFirst(s);
            System.out.println(s.title + " added to front");
        } else {
            System.out.println("Duplicate song");
        }
    }

    void addAtPosition(int position, Song s) {
        if (position < 0 || position > playlist.size()) {
            System.out.println("Invalid position");
            return;
        }

        if (!songIds.add(s.id)) {
            System.out.println("Duplicate song");
            return;
        }

        playlist.add(position, s);
    }

    void removeSong(String id) {
        ListIterator<Song> it = playlist.listIterator();

        while (it.hasNext()) {
            Song s = it.next();

            if (s.id.equals(id)) {
                it.remove();
                songIds.remove(id);
                System.out.println(s.title + " removed");
                return;
            }
        }
    }

    void playNext() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist empty");
            return;
        }

        Song s = playlist.removeFirst();
        songIds.remove(s.id);
        history.add(s);
        System.out.println("Playing: " + s.title);
    }

    void displayForward() {
        ListIterator<Song> it = playlist.listIterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    void displayBackward() {
        ListIterator<Song> it =
            playlist.listIterator(playlist.size());

        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }

    void displayHistory() {
        System.out.println("Recently played: " + history);
    }
}

public class Q2{
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();
        Song s1 = new Song("1", "Song A");
        Song s2 = new Song("2", "Song B");
        Song s3 = new Song("3", "Song C");
        player.addSong(s1);
        player.addSong(s2);
        player.addPriority(s3);
        System.out.println("Forward:");
        player.displayForward();
        System.out.println("Backward:");
        player.displayBackward();
        player.playNext();
        player.displayHistory();
    }
}