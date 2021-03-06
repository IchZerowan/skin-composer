package com.ray3k.skincomposer.dialog.scenecomposer.menulisteners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.ray3k.stripe.PopTableClickListener;
import com.ray3k.stripe.Spinner;
import com.ray3k.skincomposer.data.StyleData;
import com.ray3k.skincomposer.data.StyleProperty;
import com.ray3k.skincomposer.dialog.DialogListener;
import com.ray3k.skincomposer.dialog.scenecomposer.DialogSceneComposer;
import com.ray3k.skincomposer.dialog.scenecomposer.DialogSceneComposerEvents;
import com.ray3k.skincomposer.dialog.scenecomposer.DialogSceneComposerModel;
import com.ray3k.skincomposer.dialog.scenecomposer.DialogSceneComposerModel.SimActor;
import com.ray3k.skincomposer.dialog.scenecomposer.StyleSelectorPopTable;

public class ImageButtonListeners {
    public static EventListener imageButtonNameListener(final DialogSceneComposer dialogSceneComposer) {
        var simImageButton = (DialogSceneComposerModel.SimImageButton) dialogSceneComposer.simActor;
        var textField = new TextField("", DialogSceneComposer.skin, "scene");
        var popTableClickListener = new PopTableClickListener(DialogSceneComposer.skin) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dialogSceneComposer.getStage().setKeyboardFocus(textField);
                textField.setSelection(0, textField.getText().length());
                
                update();
            }
            
            public void update() {
                var popTable = getPopTable();
                popTable.clearChildren();
                
                var label = new Label("Name:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
                
                popTable.row();
                textField.setText(simImageButton.name);
                popTable.add(textField).minWidth(150);
                textField.addListener(DialogSceneComposer.main.getIbeamListener());
                textField.addListener(new TextTooltip("The name of the button to allow for convenient searching via Group#findActor().", DialogSceneComposer.main.getTooltipManager(), DialogSceneComposer.skin, "scene"));
                textField.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        dialogSceneComposer.events.imageButtonName(textField.getText());
                    }
                });
                textField.addListener(new InputListener() {
                    @Override
                    public boolean keyDown(InputEvent event, int keycode) {
                        if (keycode == Input.Keys.ENTER) {
                            popTable.hide();
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                
                dialogSceneComposer.getStage().setKeyboardFocus(textField);
                textField.setSelection(0, textField.getText().length());
            }
        };
        
        popTableClickListener.update();
        
        return popTableClickListener;
    }
    
    public static EventListener imageButtonStyleListener(final DialogSceneComposerEvents events, SimActor simActor) {
        var simImageButton = (DialogSceneComposerModel.SimImageButton) simActor;
        var popTableClickListener = new StyleSelectorPopTable(ImageButton.class, simImageButton.style == null ? "default" : simImageButton.style.name) {
            @Override
            public void accepted(StyleData styleData) {
                events.imageButtonStyle(styleData);
            }
        };
        
        return popTableClickListener;
    }
    
    public static EventListener imageButtonCheckedListener(final DialogSceneComposerEvents events, SimActor simActor) {
        var simImageButton = (DialogSceneComposerModel.SimImageButton) simActor;
        var popTableClickListener = new PopTableClickListener(DialogSceneComposer.skin) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                update();
            }
            
            public void update() {
                var popTable = getPopTable();
                popTable.clearChildren();
                
                var label = new Label("Checked:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
                
                popTable.row();
                var textButton = new TextButton(simImageButton.checked ? "TRUE" : "FALSE", DialogSceneComposer.skin, "scene-small");
                textButton.setChecked(simImageButton.checked);
                popTable.add(textButton).minWidth(100);
                textButton.addListener(DialogSceneComposer.main.getHandListener());
                textButton.addListener(new TextTooltip("Whether the button is checked initially.", DialogSceneComposer.main.getTooltipManager(), DialogSceneComposer.skin, "scene"));
                textButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        textButton.setText(textButton.isChecked() ? "TRUE" : "FALSE");
                        events.imageButtonChecked(textButton.isChecked());
                    }
                });
            }
        };
        
        popTableClickListener.update();
        
        return popTableClickListener;
    }
    
    public static EventListener imageButtonDisabledListener(final DialogSceneComposerEvents events, SimActor simActor) {
        var simImageButton = (DialogSceneComposerModel.SimImageButton) simActor;
        var popTableClickListener = new PopTableClickListener(DialogSceneComposer.skin) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                update();
            }
            
            public void update() {
                var popTable = getPopTable();
                popTable.clearChildren();
                
                var label = new Label("Disabled:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
                
                popTable.row();
                var textButton = new TextButton(simImageButton.disabled ? "TRUE" : "FALSE", DialogSceneComposer.skin, "scene-small");
                textButton.setChecked(simImageButton.disabled);
                popTable.add(textButton).minWidth(100);
                textButton.addListener(DialogSceneComposer.main.getHandListener());
                textButton.addListener(new TextTooltip("Whether the button is disabled initially.", DialogSceneComposer.main.getTooltipManager(), DialogSceneComposer.skin, "scene"));
                textButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        textButton.setText(textButton.isChecked() ? "TRUE" : "FALSE");
                        events.imageButtonDisabled(textButton.isChecked());
                    }
                });
            }
        };
        
        popTableClickListener.update();
        
        return popTableClickListener;
    }
    
    public static EventListener imageButtonColorListener(final DialogSceneComposerEvents events, SimActor simActor) {
        var simImageButton = (DialogSceneComposerModel.SimImageButton) simActor;
        var popTableClickListener = new PopTableClickListener(DialogSceneComposer.skin) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                update();
            }
            
            public void update() {
                var popTable = getPopTable();
                popTable.clearChildren();
                
                var label = new Label("Color:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
                
                popTable.row();
                var imageButton = new ImageButton(DialogSceneComposer.skin, "scene-color");
                imageButton.getImage().setColor(simImageButton.color == null ? Color.WHITE : simImageButton.color.color);
                popTable.add(imageButton).minWidth(100);
                imageButton.addListener(DialogSceneComposer.main.getHandListener());
                imageButton.addListener(new TextTooltip("Select the color of the button.", DialogSceneComposer.main.getTooltipManager(), DialogSceneComposer.skin, "scene"));
                imageButton.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        popTable.hide();
                        DialogSceneComposer.main.getDialogFactory().showDialogColors(new StyleProperty(), (colorData, pressedCancel) -> {
                            if (!pressedCancel) {
                                events.imageButtonColor(colorData);
                            }
                        }, new DialogListener() {
                            @Override
                            public void opened() {
                            
                            }
                            
                            @Override
                            public void closed() {
                            
                            }
                        });
                    }
                });
                
                popTable.row();
                label = new Label(simImageButton.color == null ? "white" : simImageButton.color.getName(), DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
            }
        };
        
        popTableClickListener.update();
        
        return popTableClickListener;
    }
    
    public static EventListener imageButtonPaddingListener(final DialogSceneComposerEvents events, SimActor simActor) {
        var simImageButton = (DialogSceneComposerModel.SimImageButton) simActor;
        var popTableClickListener = new PopTableClickListener(DialogSceneComposer.skin) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                update();
            }
            
            public void update() {
                var popTable = getPopTable();
                popTable.clearChildren();
                
                var changeListener = new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        Spinner padLeft = popTable.findActor("pad-left");
                        Spinner padRight = popTable.findActor("pad-right");
                        Spinner padTop = popTable.findActor("pad-top");
                        Spinner padBottom = popTable.findActor("pad-bottom");
                        events.imageButtonPadding((float) padLeft.getValue(), (float) padRight.getValue(), (float) padTop.getValue(), (float) padBottom.getValue());
                    }
                };
                
                var label = new Label("Padding:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label).colspan(2);
                
                popTable.row();
                popTable.defaults().right().spaceRight(5);
                label = new Label("Left:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
                
                var spinner = new Spinner(0, 1, true, Spinner.Orientation.RIGHT_STACK, DialogSceneComposer.skin, "scene");
                spinner.setName("pad-left");
                spinner.setValue(simImageButton.padLeft);
                popTable.add(spinner);
                spinner.getTextField().addListener(DialogSceneComposer.main.getIbeamListener());
                spinner.getButtonMinus().addListener(DialogSceneComposer.main.getHandListener());
                spinner.getButtonPlus().addListener(DialogSceneComposer.main.getHandListener());
                spinner.addListener(new TextTooltip("The padding on the left of the contents.", DialogSceneComposer.main.getTooltipManager(), DialogSceneComposer.skin, "scene"));
                spinner.addListener(changeListener);
                
                popTable.row();
                label = new Label("Right:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
                
                spinner = new Spinner(0, 1, true, Spinner.Orientation.RIGHT_STACK, DialogSceneComposer.skin, "scene");
                spinner.setName("pad-right");
                spinner.setValue(simImageButton.padRight);
                popTable.add(spinner);
                spinner.getTextField().addListener(DialogSceneComposer.main.getIbeamListener());
                spinner.getButtonMinus().addListener(DialogSceneComposer.main.getHandListener());
                spinner.getButtonPlus().addListener(DialogSceneComposer.main.getHandListener());
                spinner.addListener(new TextTooltip("The padding on the right of the contents.", DialogSceneComposer.main.getTooltipManager(), DialogSceneComposer.skin, "scene"));
                spinner.addListener(changeListener);
                
                popTable.row();
                label = new Label("Top:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
                
                spinner = new Spinner(0, 1, true, Spinner.Orientation.RIGHT_STACK, DialogSceneComposer.skin, "scene");
                spinner.setName("pad-top");
                spinner.setValue(simImageButton.padTop);
                popTable.add(spinner);
                spinner.getTextField().addListener(DialogSceneComposer.main.getIbeamListener());
                spinner.getButtonMinus().addListener(DialogSceneComposer.main.getHandListener());
                spinner.getButtonPlus().addListener(DialogSceneComposer.main.getHandListener());
                spinner.addListener(new TextTooltip("The padding on the top of the contents.", DialogSceneComposer.main.getTooltipManager(), DialogSceneComposer.skin, "scene"));
                spinner.addListener(changeListener);
                
                popTable.row();
                label = new Label("Bottom:", DialogSceneComposer.skin, "scene-label-colored");
                popTable.add(label);
                
                spinner = new Spinner(0, 1, true, Spinner.Orientation.RIGHT_STACK, DialogSceneComposer.skin, "scene");
                spinner.setName("pad-bottom");
                spinner.setValue(simImageButton.padBottom);
                popTable.add(spinner);
                spinner.getTextField().addListener(DialogSceneComposer.main.getIbeamListener());
                spinner.getButtonMinus().addListener(DialogSceneComposer.main.getHandListener());
                spinner.getButtonPlus().addListener(DialogSceneComposer.main.getHandListener());
                spinner.addListener(new TextTooltip("The padding on the bottom of the contents.", DialogSceneComposer.main.getTooltipManager(), DialogSceneComposer.skin, "scene"));
                spinner.addListener(changeListener);
            }
        };
        
        popTableClickListener.update();
        
        return popTableClickListener;
    }
}
