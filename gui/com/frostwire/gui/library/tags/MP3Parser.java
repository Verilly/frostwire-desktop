package com.frostwire.gui.library.tags;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v24Frames;

class MP3Parser extends JaudiotaggerParser {

    private static final Log LOG = LogFactory.getLog(MP3Parser.class);

    public MP3Parser(File file) {
        super(file);
    }

    protected String getTitle(AudioFile audioFile) {
        return getValueSafe(super.getTitle(audioFile), audioFile, ID3v24Frames.FRAME_ID_TITLE);
    }

    protected String getArtist(AudioFile audioFile) {
        return getValueSafe(super.getArtist(audioFile), audioFile, ID3v24Frames.FRAME_ID_ARTIST);
    }

    protected String getAlbum(AudioFile audioFile) {
        return getValueSafe(super.getAlbum(audioFile), audioFile, ID3v24Frames.FRAME_ID_ALBUM);
    }

    protected String getComment(AudioFile audioFile) {
        return getValueSafe(super.getComment(audioFile), audioFile, ID3v24Frames.FRAME_ID_COMMENT);
    }

    protected String getGenre(AudioFile audioFile) {
        return getValueSafe(super.getGenre(audioFile), audioFile, ID3v24Frames.FRAME_ID_GENRE);
    }

    protected String getTrack(AudioFile audioFile) {
        return getValueSafe(super.getTrack(audioFile), audioFile, ID3v24Frames.FRAME_ID_TRACK);
    }

    protected String getYear(AudioFile audioFile) {
        return getValueSafe(super.getYear(audioFile), audioFile, ID3v24Frames.FRAME_ID_YEAR);
    }

    private String getValueSafe(String currentValue, AudioFile audioFile, String identifier) {
        String value = currentValue;

        if (value == null || value.length() == 0) {
            if (audioFile instanceof MP3File && ((MP3File) audioFile).hasID3v2Tag()) {
                try {
                    AbstractID3v2Tag v2tag = ((MP3File) audioFile).getID3v2Tag();
                    value = v2tag.getFirst(identifier);
                } catch (Exception e) {
                    LOG.warn("Unable to get value for ID3v2 tag key: " + identifier, e);
                }
            }
        }

        return value;
    }
}
