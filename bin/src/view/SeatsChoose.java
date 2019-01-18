package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controll.MysqlExe;
import data.Train;

public class SeatsChoose extends JDialog {

    Train train;
    int tickets_count;//�i�H�諸��m�ơA�]�N�O�ʶR���ƶq
    int car;//���[
    JButton msg;
    boolean confirmed = false;

    JLabel background = new JLabel(new ImageIcon(getClass().getResource("/img/train_1.jpg")));
    JCheckBox[][] checkBoxes = new JCheckBox[14][6];
    String[] seats;

    public SeatsChoose(Frame owner, Train train, int car, int count, JButton msg) {
        super(owner, true);
        this.train = train;
        this.car = car;
        this.msg = msg;
        tickets_count = count;
        //Frame
        setTitle("��ܮy��]��"  + car + "�`���[�^");
        setBounds(400, 50, 350, 950);
        setResizable(false);
        setLayout(new BorderLayout());

        //Panel
        GridLayout gridLayout = new GridLayout(13, 6);
        gridLayout.setVgap(25);
        gridLayout.setHgap(10);
        getContentPane().setLayout(gridLayout);
        JPanel imagePanel = (JPanel) this.getContentPane();//�⤺�e�����ରJPanel�A�_�h����ϥ�setOpaque()�Өϵ����ܦ��z��
        imagePanel.setOpaque(false);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(170, 30, 0, 0));//�t�XCheckBox
        initialize_box();
        checkSeats();

        //background
        background.setBounds(0, 0, 350, 950);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));     // ��I���ϲK�[����h���檺�̩��h�H�@���I��
        setVisible(true);
    }

    void initialize_box() {
        //�إ�CheckBox
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 6; j++) {
                if(!(i == 1 && j > 2)) {
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setOpaque(false);
                    if (i == 0 || j == 3)
                        checkBox.setVisible(false);
                    checkBoxes[i][j] = checkBox;
                }
            }
        }
        //�NCheckBoxs�[�JLayout
        for(int y = 13; y > 0; y--){
            for(int x = 0; x < 6; x++){
                if(!(y == 1 && x > 2))
                    getContentPane().add(checkBoxes[y][x]);
            }
        }
        //�[�Jreturn����
        JButton button = new JButton("�T�{");
        button.setBounds(225,40,100,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seats = new String[tickets_count];
                int count = 0;
                for(int i = 0; i < 14; i++){
                    for(int j = 0; j < 6; j++){
                        if(!(i == 1 && j > 2)){
                            if(checkBoxes[i][j].isSelected()){
                                count++;
                                if(count <= tickets_count)
                                    seats[count-1] = String.format("%02d%02d", car, i) + getSeatColumn(j);
                                else{
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "�u����" + tickets_count + "�Ӯy��I",
                                            "InfoBox: Failed",
                                            JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                            }
                        }
                    }
                }
                if(count < tickets_count){
                    JOptionPane.showMessageDialog(
                            null,
                            "�ݭn���" + tickets_count + "�Ӯy��I",
                            "InfoBox: Failed",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //��ܦ��\
                confirmed = true;
                msg.setText("�w��");
                setVisible(false);
            }
        });
        getLayeredPane().add(button, new Integer(2));
    }


    void checkSeats(){
        //SQL
        MysqlExe.RetVal ret = null;
        try {
            ret = MysqlExe.execQuery(String.format(
                    "SELECT * FROM tickets WHERE train_id = %d AND date = %d",
                    train.train_id, train.date
            ));
            while (ret.res.next()){
                String seat_id = ret.res.getString("seat_id");
                System.out.println(seat_id);
                if(seat_id.substring(0,2).equals(String.format("%02d", car))){//�P���[
                    int row = Integer.parseInt(seat_id.substring(2, 4));
                    int column = getSeatIndex(seat_id.charAt(4));
                    checkBoxes[row][column].setEnabled(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    int getSeatIndex(char column){
        int index = column - 65;
        //DE
        index = index>2? index-1: index;
        return index;
    }

    String getSeatColumn(int index){
        char s = (char) (index + 65);
        s = s>67? (char) (s + 1) :s;
        return "" + s;
    }

    String getString(){
        String s = "�����w\n";
        if(confirmed){
            s = "";
            for(String seat:seats){
                s = s + seat + "\n";
            }
        }
        return s;
    }
}
