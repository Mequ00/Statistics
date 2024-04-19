package ru.kristin.laba2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;

public class GUI extends javax.swing.JFrame {

    Manager manager = new Manager();

    public GUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        ButtonExport = new javax.swing.JButton();
        ButtonImport = new javax.swing.JButton();
        ButtonExit = new javax.swing.JButton();
        TextFieldForVariant = new javax.swing.JTextField();
        RadioButtonIsIndex = new javax.swing.JRadioButton();
        LabelVariant = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ButtonExport.setText("export");
        ButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonExportActionPerformed(evt);
            }
        });

        ButtonImport.setText("import");
        ButtonImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonImportActionPerformed(evt);
            }
        });

        ButtonExit.setText("exit");
        ButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonExitActionPerformed(evt);
            }
        });

        RadioButtonIsIndex.setText("Выбрать варинат числом");
        RadioButtonIsIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonIsIndexActionPerformed(evt);
            }
        });

        LabelVariant.setText("Введите вариант текстом");

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addComponent(LabelVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RadioButtonIsIndex))
                    .addComponent(TextFieldForVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ButtonImport)
                        .addComponent(ButtonExport)
                        .addComponent(ButtonExit)))
                .addGap(96, 96, 96))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RadioButtonIsIndex))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldForVariant, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonExport)
                .addGap(18, 18, 18)
                .addComponent(ButtonImport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(ButtonExit)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonImportActionPerformed
        if (TextFieldForVariant.getText().trim().isBlank()) {
            JOptionPane.showMessageDialog(this, "Укажите вариант");
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Exel files", "xlsx"));
        int window = fileChooser.showDialog(this, "Выберете файл");
        if (window == JFileChooser.APPROVE_OPTION) {
            try {
                manager.Import(fileChooser.getSelectedFile().getAbsolutePath(),
                        TextFieldForVariant.getText(), RadioButtonIsIndex.isSelected());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Вариант указан неверно(укажите число)");
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "Вариант указан неверно");
            } catch (NotOfficeXmlFileException e) {
                JOptionPane.showMessageDialog(this, "Указан неверный формат файла");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Вариант указан неверно(нет листа по такому индексу)");
            } catch (IllegalStateException e) {
                JOptionPane.showMessageDialog(this, "Выбран не тот файл");
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Системе не удается найти указанный путь");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Произошла ошибка при чтении файла");
            }
        }
    }//GEN-LAST:event_ButtonImportActionPerformed

    private void ButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExportActionPerformed
        if (manager.getStorage().getData().isEmpty()) {
            JOptionPane.showMessageDialog(this, "вы ещё не загрузили данные");
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        int window = fileChooser.showDialog(this, "Выберете файл");
        if (window == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".xlsx")) {
                filePath += ".xlsx";
            }
            try {
                manager.Export(filePath);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Ошибка при записи файла");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Ошибка при записи файла");
            }
        }
    }//GEN-LAST:event_ButtonExportActionPerformed

    private void ButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ButtonExitActionPerformed

    private void RadioButtonIsIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonIsIndexActionPerformed
        if (RadioButtonIsIndex.isSelected()) {
            LabelVariant.setText("Введите вариант числом");
        } else {
            LabelVariant.setText("Введите вариант текстом");
        }
    }//GEN-LAST:event_RadioButtonIsIndexActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonExit;
    private javax.swing.JButton ButtonExport;
    private javax.swing.JButton ButtonImport;
    private javax.swing.JLabel LabelVariant;
    private javax.swing.JPanel Panel;
    private javax.swing.JRadioButton RadioButtonIsIndex;
    private javax.swing.JTextField TextFieldForVariant;
    // End of variables declaration//GEN-END:variables
}
