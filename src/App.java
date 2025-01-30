import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App {
    private static DefaultListModel<Collection> collectionListModel = new DefaultListModel<Collection>();
    private static DefaultListModel<Album> albumListModel = new DefaultListModel<Album>();
    private static DefaultListModel<Song> songListModel = new DefaultListModel<Song>();
    
    private static JList<Collection> collectionJList = new JList<Collection>();
    private static JList<Album> albumJList = new JList<Album>();
    private static JList<Song> songJList = new JList<Song>();
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Music Collection");

        // Main Panel
        JPanel main = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        main.setLayout(layout);
        main.setBackground(Color.BLACK);



        // Collections
        collectionListModel.addAll(CollectionORM.getCollections());

        collectionJList.setModel(collectionListModel);

        // Scroll pane
        JScrollPane spCollections = new JScrollPane(collectionJList);
        
        spCollections.getViewport().getView().setBackground(Color.BLACK);
        spCollections.getViewport().getView().setForeground(Color.decode("#33FF00"));
        spCollections.setPreferredSize(new Dimension(300, 600));

        // Get albums of selected collections
        JButton getAlbums = new JButton("Get Albums");
        getAlbums.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                albumListModel.removeAllElements();
                albumListModel.addAll(collectionJList.getSelectedValue().getAlbums());
            }
        });

        // Add album to selected collection
        JButton addAlbum = new JButton("Add Album");
        addAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (collectionJList.getSelectedValue() == null || albumJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select collection and album.");
                    return;
                }
                collectionJList.getSelectedValue().addAlbum(albumJList.getSelectedValue());
                int collectionId = collectionJList.getSelectedValue().getId();
                int albumId = albumJList.getSelectedValue().getId(); 
                CollectionORM.addAlbum(collectionId,albumId);
                JOptionPane.showMessageDialog(null, "Album successfully added to Collection");
            }
        });

        // remove Album from selected collection
        JButton removeAlbum = new JButton("Remove Album");
        removeAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (collectionJList.getSelectedValue() == null || albumJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select collection and album.");
                    return;
                }

                for (Album album : collectionJList.getSelectedValue().getAlbums()) {
                    if (albumJList.getSelectedValue().getId() == album.getId()) {
                        CollectionORM.removeAlbum(collectionJList.getSelectedValue().getId(), album.getId());
                        JOptionPane.showMessageDialog(null, "Album successfully removed from Collection");
                        refreshAlbums();
                        refreshCollections();
                        return;
                    }
                }

                JOptionPane.showMessageDialog(null, "Album is no in selected Collection");

            }
        });

        JOptionPane jop = new JOptionPane();

        JDialog newCollectionDialog = jop.createDialog("New Collection");
        newCollectionDialog.setSize(250, 200);

        // New Collection 
        JButton newCollection = new JButton("New Collection");
        newCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CollectionPanel newCollectionPanel = new CollectionPanel();
                newCollectionPanel.newCollection();

                newCollectionDialog.setContentPane(newCollectionPanel);
                newCollectionDialog.setVisible(true);
                refreshCollections();
            }
        });

        JDialog updateCollectionDialog = jop.createDialog("Update Collection");
        updateCollectionDialog.setSize(250, 200);

        // Update Collection 
        JButton updateCollection = new JButton("Update Collection");
        updateCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (collectionJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select collection.");
                    return;
                }

                int id = collectionJList.getSelectedValue().getId();
                String title = collectionJList.getSelectedValue().getTitle();
                String nameOfOwner = collectionJList.getSelectedValue().getNameOfOwner();

                CollectionPanel updateCollectionPanel = new CollectionPanel();
                updateCollectionPanel.updateCollection(id, title, nameOfOwner);

                updateCollectionDialog.setContentPane(updateCollectionPanel);
                updateCollectionDialog.setVisible(true);
                refreshCollections();
            }
        });

        // Delete Collection 
        JButton deleteCollection = new JButton("Delete Collection");
        deleteCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (collectionJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select collection.");
                    return;
                }

                int collectionId = collectionJList.getSelectedValue().getId();

                CollectionORM.deleteCollection(collectionId);
                refreshCollections();
            }
        });



        // Albums
        albumListModel.addAll(AlbumORM.getAlbums());

        albumJList.setModel(albumListModel);

        // Scroll pane
        JScrollPane spAlbums = new JScrollPane(albumJList);
        
        spAlbums.getViewport().getView().setBackground(Color.BLACK);
        spAlbums.getViewport().getView().setForeground(Color.decode("#33FF00"));
        spAlbums.setPreferredSize(new Dimension(300, 600));

        // Get songs of selected album
        JButton getSongs = new JButton("Get Songs");
        getSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songListModel.removeAllElements();
                songListModel.addAll(albumJList.getSelectedValue().getSongs());
            }
        });

        // Add song to selected album
        // JButton addSong = new JButton("Add Song");
        // addSong.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         //
        //     }
        // });

        // Get Collections
        JButton getCollections = new JButton("Get Collections");
        getCollections.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectionListModel.removeAllElements();
                collectionListModel.addAll(albumJList.getSelectedValue().getCollections());
            }
        });

        JDialog newAlbumDialog = jop.createDialog("New Album");
        newAlbumDialog.setSize(250, 200);

        // New Album
        JButton newAlbum = new JButton("New Album");
        newAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlbumPanel newAlbumPanel = new AlbumPanel();
                newAlbumPanel.newAlbum();

                newAlbumDialog.setContentPane(newAlbumPanel);
                newAlbumDialog.setVisible(true);
                refreshAlbums();
            }
        });

        JDialog updateAlbumDialog = jop.createDialog("Update Album");
        updateAlbumDialog.setSize(250, 200);

        // Update Album
        JButton updateAlbum = new JButton("Update Album");
        updateAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (albumJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select album.");
                    return;
                }

                int id = albumJList.getSelectedValue().getId();
                String title = albumJList.getSelectedValue().getTitle();
                String author = albumJList.getSelectedValue().getAuthor();
                String genre = albumJList.getSelectedValue().getGenre();
                int year = albumJList.getSelectedValue().getYear();

                AlbumPanel updateAlbumPanel = new AlbumPanel();
                updateAlbumPanel.updateAlbum(id, title, author, genre, year);

                updateAlbumDialog.setContentPane(updateAlbumPanel);
                updateAlbumDialog.setVisible(true);
                refreshAlbums();
            }
        });

        // Delete Album
        JButton deleteAlbum = new JButton("Delete Album");
        deleteAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (albumJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select album.");
                    return;
                }

                int albumId = albumJList.getSelectedValue().getId();

                AlbumORM.deleteAlbum(albumId);
                refreshAlbums();
            }
        });

        // Songs
        songListModel.addAll(SongORM.getSongs());

        songJList.setModel(songListModel);

        // Scroll pane
        JScrollPane spSongs = new JScrollPane(songJList);

        spSongs.getViewport().getView().setBackground(Color.BLACK);
        spSongs.getViewport().getView().setForeground(Color.decode("#33FF00"));
        spSongs.setPreferredSize(new Dimension(300, 600));

        // Search song
        JButton searchSong = new JButton("Search Song");
        searchSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchSong();
            }
        });

        // get Album
        JButton getAlbum = new JButton("Get Album");
        getAlbum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                albumListModel.removeAllElements();
                albumListModel.add(0, songJList.getSelectedValue().getAlbum());
            }
        });

        JDialog newSongDialog = jop.createDialog("New Album");
        newSongDialog.setSize(250, 200);

        // New song
        JButton newSong = new JButton("New Song");
        newSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (albumJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select album that contains new song.");
                    return;
                }

                SongPanel newSongPanel = new SongPanel();
                newSongPanel.newSong(albumJList.getSelectedValue().getId());

                newSongDialog.setContentPane(newSongPanel);
                newSongDialog.setVisible(true);
                refreshSongs();
                refreshAlbums();
            }
        });
        
        // Update song
        JDialog updateSongDialog = jop.createDialog("Update Album");
        updateSongDialog.setSize(250, 200);

        JButton updateSong = new JButton("Update Song");
        updateSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (songJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select song.");
                    return;
                }

                int id = songJList.getSelectedValue().getId();
                String title = songJList.getSelectedValue().getTitle();
                int duration = songJList.getSelectedValue().getDuration();

                SongPanel updateSongPanel = new SongPanel();
                updateSongPanel.updateSong(id, title, duration);

                updateSongDialog.setContentPane(updateSongPanel);
                updateSongDialog.setVisible(true);
                refreshSongs();
                refreshAlbums();
            }
        });

        // Delete song
        JButton deleteSong = new JButton("Delete Song");
        deleteSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (songJList.getSelectedValue() == null) {
                    JOptionPane.showMessageDialog(null, "Please select song.");
                    return;
                }

                int songId = songJList.getSelectedValue().getId();

                SongORM.deleteSong(songId);
                refreshSongs();
                refreshAlbums();
            }
        });

        // Back to Main
        JButton backToMain = new JButton("Back to Main");
        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshAlbums();
                refreshSongs();
                refreshCollections();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        
        // Display Collections Scroll Panel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        main.add(spCollections, gbc);

        // Get Album Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        main.add(getAlbums, gbc);
        
        // Add Album Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        main.add(addAlbum, gbc);

        // New Collection Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        main.add(newCollection, gbc);

        // Update Collection Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        main.add(updateCollection, gbc);

        // Delete Collection Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        main.add(deleteCollection, gbc);

        // remove Album Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        main.add(removeAlbum, gbc);


        // Display Album Scroll Panel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        main.add(spAlbums, gbc);

        // Get Songs Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        main.add(getSongs, gbc);

        // Add Song Button
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        // gbc.gridx = 3;
        // gbc.gridy = 1;
        // gbc.gridwidth = 1;

        // main.add(addSong, gbc);

        // Get Collections Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        main.add(getCollections, gbc);

        // New Album Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        main.add(newAlbum, gbc);

        // Update Album Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        main.add(updateAlbum, gbc);

        // Delete Album Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        main.add(deleteAlbum, gbc);

        // Display Songs Scroll Panel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        main.add(spSongs, gbc);

        // Search Song Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        main.add(searchSong, gbc);

        // Get Album
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        main.add(getAlbum, gbc);

        // New Song Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        main.add(newSong, gbc);

        // Update Song Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        main.add(updateSong, gbc);

        // Remove Song Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        main.add(deleteSong, gbc);

        // Back to Main Button
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridx = 6;
        gbc.gridy = 0;

        main.add(backToMain, gbc);

        frame.add(main);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void searchSong() {
        String songTitle = JOptionPane.showInputDialog("Enter song title to search for:");

        boolean flag_found = false;

        ArrayList<Song> result = new ArrayList<Song>();

        if(songTitle == null) {
            return;
        }

        for (Song song : SongORM.getSongs()) {
            if (song.getTitle().toLowerCase().equals(songTitle.toLowerCase())) {
                result.add(song);
                songListModel.removeAllElements();
                flag_found = true;
            }
        }
        
        if (flag_found) {
            songListModel.addAll(result);
        } 
        else {
            JOptionPane.showMessageDialog(null, "Could not find the song " + songTitle);
        }
    }

    private static void refreshCollections() {
        collectionListModel.removeAllElements();
        CollectionORM.fetchCollections();
        collectionListModel.addAll(CollectionORM.getCollections());
    }

    private static void refreshAlbums() {
        albumListModel.removeAllElements();
        AlbumORM.fetchAlbums();
        albumListModel.addAll(AlbumORM.getAlbums());
    }

    private static void refreshSongs() {
        songListModel.removeAllElements();
        SongORM.fetchSongs();
        songListModel.addAll(SongORM.getSongs());
    }
    public static void main(String[] args) throws Exception {
        SongORM.fetchSongs();
        AlbumORM.fetchAlbums();
        CollectionORM.fetchCollections();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}