package gamelogics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import extra.Position;
import pieces.ChessPiece;
import players.Player;

public class EasyChessGame extends ChessGameLogic{

	public EasyChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}


	public static void colorValidPositions(ArrayList<Position> positionsHolder)
	{
		for (Position position : positionsHolder)
		{
			if (chessBoard.hasPieceInPositon(position))
			{
				  squares[position.getRow()][position.getColumn()].setBorder(new LineBorder(Color.yellow , 3));
			}
			else
			{
			  squares[position.getRow()][position.getColumn()].setBorder(new LineBorder(Color.green , 3));
			}
		}
		
	}
	
	private void pressedButton(Position buttonPosition)
	{		
		if (!squares[buttonPosition.getRow()][buttonPosition.getColumn()].getBorder().equals(new JButton().getBorder()))
	      {
	    	 // colored
	    	  if(chessBoard.hasPieceInPositon(buttonPosition))
	    	  {
	    		  ChessPiece enemy = chessBoard.getPiece(buttonPosition);
	    		  squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(null);
	    		  chessBoard.pieceCaptured(enemy);
	    	  }	
	    		  ImageIcon iconHolder = (ImageIcon) squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].getIcon();
	    		  squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
	    	      squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(iconHolder);
	    	      removeColoredBorder();
	    	      currentPiece.setCurrentPosition(buttonPosition);
	    	      currentPiece = null;    	      
	    	      playTurn++;
	      }
	      else
	      {
	    	 // uncolored
	    	  if (chessBoard.hasPieceInPositon(buttonPosition))
	    	  {
	    		  if(hasCurrentPiece())
	    		  {
	    			  removeColoredBorder();
	    		  }
	    		  currentPiece = chessBoard.getPiece(buttonPosition);
	    		  colorValidPositions(chessBoard.getValidPositions(currentPiece));
	    	  }
	    	  else removeColoredBorder();
	    	  
	      }
	  
	}
	
	public static void removeColoredBorder()
	{
		for (Position position : chessBoard.getValidPositions(currentPiece))
		{
			System.out.println(position.getRow() + " " + position.getColumn());
			squares[position.getRow()][position.getColumn()].setBorder(UIManager.getBorder("Button.border"));
		}
	}
	
	public static boolean hasCurrentPiece()
	{
		return(currentPiece != null);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		for (int i = 0;i<squares.length;i++)
		{
			for (int j=0;j<squares.length;j++)
			{
				if (event.getSource() == squares[i][j])
				{
					ChessPiece holder = chessBoard.getPiece(new Position(i,j));;
					if (currentPiece != null)
					{
						holder = currentPiece;
					}
				
					if(squares[i][j].getIcon()==null || EasyChessGame.playTurn % 2 == 0 && holder.getPieceColor().equals("White") 
							||EasyChessGame.playTurn % 2 == 1 && holder.getPieceColor().equals("Black"))
						           pressedButton(new Position(i,j));
				}
			}
		}
	}
	
	
	
	
	
}
