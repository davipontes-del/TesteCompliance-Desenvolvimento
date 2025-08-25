package janela_basica_teste;
import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.util.Map;

public class TelaPrincipal extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private CardLayout cardLayout;
    private JPanel painelPrincipal;
    private ImageIcon imagemLateral;
    
    public TelaPrincipal() {
        setTitle("Tela de Login 1.00.00");
        setSize(580, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);
        
        imagemLateral = new ImageIcon("C:\\Users\\dpontes\\Desktop\\teste compliance - desenvolvimento\\janela_basica_teste\\src\\imagens\\bricoHeader_janela_teste.png");
        
        //Factory Method
        JPanel cardLogin = criarCardLogin();
        JPanel cardOpcoes = criarCardOpcoes();
        
        painelPrincipal.add(cardLogin, "Login");
        painelPrincipal.add(cardOpcoes, "Opcoes");
        
        add(painelPrincipal);
        setVisible(true);
    }
    
    private JPanel criarCardLogin() {
        JPanel panelconteudo = new JPanel();
        panelconteudo.setLayout(new BoxLayout(panelconteudo, BoxLayout.Y_AXIS));
        panelconteudo.setBorder(BorderFactory.createEmptyBorder(40, 20, 10, 20));
        panelconteudo.setMinimumSize(new Dimension(100, 300));
        panelconteudo.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        
    JPanel painelImagem = new JPanel(new BorderLayout());
    JLabel imagemLabel = new JLabel(imagemLateral);
    imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imagemLabel.setVerticalAlignment(SwingConstants.CENTER);
    painelImagem.add(imagemLabel, BorderLayout.CENTER);
        
        JLabel labelUsuario = new JLabel("Digite seu usuário:");
        labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoUsuario = new JTextField(25);
        campoUsuario.setPreferredSize(new Dimension(230, 23));
        campoUsuario.setMaximumSize(new Dimension(230, 23));
        
        panelconteudo.add(labelUsuario);
        panelconteudo.add(campoUsuario);
        panelconteudo.add(Box.createVerticalStrut(10));
        
        JLabel labelSenha = new JLabel("Digite sua senha:");
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha = new JPasswordField(25);
        campoSenha.setPreferredSize(new Dimension(230, 23));
        campoSenha.setMaximumSize(new Dimension(230, 23));
        
        panelconteudo.add(labelSenha);
        panelconteudo.add(campoSenha);
        panelconteudo.add(Box.createVerticalStrut(10));
        JPanel painelparenteRecuperar = new JPanel();
        JButton botaoRecuperarSenha = new JButton("Recuperar Senha");
        painelparenteRecuperar.add(botaoRecuperarSenha);
        botaoRecuperarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoRecuperarSenha.setBorderPainted(false);
        botaoRecuperarSenha.setContentAreaFilled(false);
        botaoRecuperarSenha.setForeground(Color.BLUE);
        painelparenteRecuperar.setLayout(new FlowLayout(FlowLayout.LEFT));
        Font font = botaoRecuperarSenha.getFont();
        Map<TextAttribute, Object> attributes = (Map<TextAttribute, Object>) font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        botaoRecuperarSenha.setFont(font.deriveFont(attributes));
        
        JPanel painelparenteBotoes = new JPanel();
        painelparenteBotoes.setLayout(new BoxLayout(painelparenteBotoes, BoxLayout.X_AXIS));
        JButton botaoSair = new JButton("Sair");
        JButton botaoEntrar = new JButton("Entrar");
        JButton botaoOpcoes = new JButton("Opções");
        getRootPane().setDefaultButton(botaoEntrar);
        painelparenteBotoes.add(botaoSair);
        painelparenteBotoes.add(Box.createRigidArea(new Dimension(10, 0)));
        painelparenteBotoes.add(botaoEntrar);
        painelparenteBotoes.add(Box.createRigidArea(new Dimension(10, 0)));
        painelparenteBotoes.add(botaoOpcoes);
        
        panelconteudo.add(painelparenteRecuperar);
        panelconteudo.add(painelparenteBotoes);
        
        JPanel painelWrapper = new JPanel(new GridLayout(1, 2, 10, 0));
        painelWrapper.add(painelImagem);
        painelWrapper.add(panelconteudo);
        
    Border bordaErro = BorderFactory.createLineBorder(new Color(200, 0, 0), 1, true); 
    Border bordaPadrao = UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border");
    Color fundoPadrao = campoUsuario.getBackground();
    Color fundoErro = new Color(255, 230, 230);
   
        botaoRecuperarSenha.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoRecuperarSenha.setForeground(new Color(0, 0, 128));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                botaoRecuperarSenha.setForeground(Color.BLUE);
            }});
        botaoSair.addActionListener(e -> dispose());
    botaoEntrar.addActionListener((ActionEvent e) -> {
            String usuario = campoUsuario.getText().trim();
            String senha = new String(campoSenha.getPassword()).trim();
            boolean camposVazios = false;
    if (usuario.isEmpty()) {
            campoUsuario.setBorder(bordaErro);
            campoUsuario.setBackground(fundoErro);
            camposVazios = true;
    } else {
            campoUsuario.setBorder(bordaPadrao);
            campoUsuario.setBackground(fundoPadrao);
        }
    if (senha.isEmpty()) {
            campoSenha.setBorder(bordaErro);
            campoSenha.setBackground(fundoErro);
            camposVazios = true;
        } else {
            campoSenha.setBorder(bordaPadrao);
            campoSenha.setBackground(fundoPadrao);
        }
    if (camposVazios) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
    return;
    }
        int escolha = JOptionPane.showConfirmDialog(
            this, "Deseja continuar?", "Confirmação", JOptionPane.OK_CANCEL_OPTION);
        if (escolha == JOptionPane.OK_OPTION) {
            Timer timer = new Timer(2000, evt -> {
                dispose();
                ((Timer) evt.getSource()).stop();
            });
        timer.setRepeats(false);
        timer.start();
        }
        });
        botaoOpcoes.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "Opcoes");
        });
        botaoRecuperarSenha.addActionListener(e -> {
        try {
            Desktop.getDesktop().browse(new java.net.URI("https://www.bricobread.com.br/contato"));
        } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao abrir o site, tente novamente mais tarde");
            }
        });
    return painelWrapper;
    }
    private JPanel criarCardOpcoes() {
        JPanel painelWrapper = new JPanel(new GridLayout(1, 2, 10, 0));
        JPanel painelImagem = new JPanel(new BorderLayout());
        JLabel imagemLabel = new JLabel(imagemLateral);
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagemLabel.setVerticalAlignment(SwingConstants.CENTER);
        painelImagem.add(imagemLabel, BorderLayout.CENTER);
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel titulo = new JLabel("Opções");
        JButton botaoSuporte = new JButton("Suporte");
        JCheckBox autoLogin = new JCheckBox("Login automático");
        JButton voltar = new JButton("Voltar");
        painelConteudo.add(titulo);
        painelConteudo.add(Box.createVerticalStrut(15));
        painelConteudo.add(botaoSuporte);
        painelConteudo.add(autoLogin);
        painelConteudo.add(Box.createVerticalStrut(15));
        painelConteudo.add(voltar);
        voltar.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "Login");
        });
        botaoSuporte.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new java.net.URI("https://www.bricobread.com.br/contato"));
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir o site, tente denovo mais tarde");
              }
        });
        
            painelWrapper.add(painelImagem);
            painelWrapper.add(painelConteudo);
    return painelWrapper;
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }
new TelaPrincipal();
});
}
}
