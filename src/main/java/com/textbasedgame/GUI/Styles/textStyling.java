package com.textbasedgame.GUI.Styles;

import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

import java.awt.Font;

import com.textbasedgame.GUI.gui;
import com.textbasedgame.world.world.CharacterNames;

public class textStyling {

    private static final HashMap<CharacterNames, DialogueInfo> characterToStyling = new HashMap<>();
    private static final int standardFontSize = 18;

    /**
     * Initializes the characterToStyling map with character-specific styling information.
     * MUST BE CALLED BEFORE ANY DIALOGUE IS DISPLAYED, OTHERWISE THE CHARACTER WILL NOT HAVE STYLING.
     */
    public static void createCharacterStylingMap(){
        characterToStyling.put(CharacterNames.LIZZY, new DialogueInfo(Color.green, Font.getFont("TeXGyreChorus") , "Lizzy"));
        characterToStyling.put(CharacterNames.IGGY, new DialogueInfo(Color.RED, Font.getFont("DejaVu Serif") , "Iggy"));
        characterToStyling.put(CharacterNames.PLAYER, new DialogueInfo(Color.gray, Font.getFont("Arial").deriveFont(Font.ITALIC), ""));
    } 

    public static void styleText(JLabel text, gui.styles style){
        
        if(style == null) return;
        switch(style){
            case BOLD:
                text.setFont(text.getFont().deriveFont(Font.BOLD));
                break;
            case ITALICS:
                text.setFont(text.getFont().deriveFont(Font.ITALIC));
                break;
            case UNDERLINE:
                text.setFont(text.getFont().deriveFont(Font.PLAIN));
                text.setText("<html><u>" + text.getText() + "</u></html>");
                break;
        }


    }

    public static void giveLabelsColorAndShape(JPanel panel, int fontSize, Color color){
        for(java.awt.Component comp : panel.getComponents()){
            if(comp instanceof JLabel){
                JLabel l = (JLabel)comp;
                java.awt.Font oldFont = l.getFont();
                l.setFont(new Font(oldFont.getName(), oldFont.getStyle(), fontSize ));
                l.setForeground(color);
            }
        }
    }

    public static void giveLabelColorAndShape(JLabel label, int fontSize, Color color){
        java.awt.Font oldFont = label.getFont();
        label.setFont(new Font(oldFont.getName(), oldFont.getStyle(), fontSize ));
        label.setForeground(color);
    }
    public static void giveLabelColorAndShape(JLabel label, Font f, int fontSize, Color color){
        label.setFont(new Font(f.getFontName(), f.getStyle(), fontSize));
        label.setForeground(color);
    }

    public static void styleDialogue(JLabel text, CharacterNames character){
        
        DialogueInfo info = characterToStyling.get(character);
        if(info == null){return;}

        if(!info.characterName.equals("")){
            text.setText(info.characterName + ": " + text.getText());
        }
        giveLabelColorAndShape(text, info.characterFont, standardFontSize, info.characterColor);
    }

    private static class DialogueInfo {
        public final Color characterColor;
        public final Font characterFont;
        public final String characterName;

        DialogueInfo(Color cColor, Font cFont, String cName){
            this.characterColor = cColor;
            this.characterFont = cFont;
            this.characterName = cName;
        }
        
    }

}
