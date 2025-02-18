#encoding:utf-8

module Irrgarten
	
	class Labyrinth
		private
			@@BLOCK_CHAR='X'
			@@EMPTY_CHAR='-'
			@@MONSTER_CHAR='M'
			@@COMBAT_CHAR='C'
			@@EXIT_CHAR='E'
			@@ROW=0
			@@COL=1
			
			def pos_ok(r,c)
				if (r < @n_rows) and (c < @n_cols) and (r >= 0) and (c >= 0)
					return true;
				else
					return false
				end
			end
			
			def empty_pos(r,c)
				if @character[r][c] == @@EMPTY_CHAR
					return true
				else
					return false
				end
			end
			
			def monster_pos(r,c)
				if @character[r][c] == @@MONSTER_CHAR
					return true
				else
					return false
				end
			end
			
			def exit_pos(r,c)
				if @character[r][c] == @@EXIT_CHAR
					return true
				else
					return false
				end
			end
			
			def combat_pos(r,c)
				if @character[r][c] == @@COMBAT_CHAR
					return true
				else
					return false
				end
			end
			
			def can_step_on(r,c)
				if pos_ok(r,c)
					if empty_pos(r,c) or monster_pos(r,c) or exit_pos(r,c)
						return true
					else
						return false
					end
				else
					return false
				end
			end
			
			def update_old_pos(r,c)
				if pos_ok(r,c)
					if combat_pos(r,c)
						@character[r][c] = @@MONSTER_CHAR
					else
						@character[r][c] = @@EMPTY_CHAR
					end
				end
			end
			
			def dir_2_pos(r,c,d)
				nr = r
				nc = c
				if d == Irrgarten::Directions::LEFT
					nc = nc -1
				elsif d == Irrgarten::Directions::RIGHT
					nc = nc + 1
				elsif d == Irrgarten::Directions::UP
					nr = nr - 1
				elsif d == Irrgarten::Directions::DOWN
					nr = nr +1
				end
				salida = [nr,nc]
				return salida
			end
			
			def random_empty_pos()
				r = Dice.random_pos(@n_rows)
				c = Dice.random_pos(@n_cols)
				while empty_pos(r,c) == false
					r = Dice.random_pos(@n_rows)
					c = Dice.random_pos(@n_cols)
				end
				salida=[r,c]
				return salida
			end
			
			def put_player_2d(orow,ocol,r,c,p)
				output = nil
				if (can_step_on(r, c)) 
					if (pos_ok(orow, ocol))
						play=@player[orow][ocol]
						if (play==p)
							update_old_pos(orow,ocol)
							@player[orow][ocol]=nil
						end
					end
					monsterpos=monster_pos(r, c)
					if (monsterpos==true)
						@character[r][c]=@@COMBAT_CHAR
						output=@monster[r][c]
					else
						@character[r][c]=p.get_number.to_s #Añadido el to_s
					end
					@player[r][c]=p
					p.set_pos(r, c)
				end
				return output
			end
			
		public
			def initialize(nr,nc,er,ec) 
				@n_rows=nr
				@n_cols=nc
				@exit_row=er
				@exit_col=ec
				@monster= Array.new(@n_rows) {Array.new(@n_cols, nil)}
				@player=Array.new(@n_rows) {Array.new(@n_cols, nil)}
				@character=Array.new(@n_rows) {Array.new(@n_cols, @@EMPTY_CHAR)}
				i = 0;
				while i < @n_rows
					j = 0;
					while j < @n_cols
						@character[i][j]=@@EMPTY_CHAR
						j+=1
					end
					i+=1
				end
				@character[@exit_row][@exit_col]=@@EXIT_CHAR
			end
			
			def spread_players(p)
				for i in 0..p.size()-1
					pos=random_empty_pos()
					put_player_2d(-1, -1, pos[0], pos[1], p[i])
				end
			end
			
			def put_fuzzy_player(fp)
				
				@player[fp.get_row][fp.get_col] = fp
			end
			def have_a_winner
				if @character[@exit_row][@exit_col]!=@@EXIT_CHAR
					return true
				else 
					return false
				end
			end
			
			def to_s
				salida = ""
				i=0
				while i < @n_rows
					j=0
					while j < @n_cols
						salida = salida + @character[i][j]+" "
						j=j+1
					end
					i= i+1
					salida = salida + "\n"
				end
				
				return salida
			end
			
			def add_monster(r,c,m)
				if @character[r][c]==@@EMPTY_CHAR
					@monster[r][c]=m
					@monster[r][c].set_pos(r,c)
					@character[r][c]=@@MONSTER_CHAR
				end
			end
			
			def put_player(d,p)
				orow=p.get_row()
				ocol=p.get_col()
				new_pos=dir_2_pos(orow, ocol, d)
				monst=put_player_2d(orow, ocol, new_pos[@@ROW], new_pos[@@COL], p)
				return monst
			end
			
			def add_block(o,sr,sc,l)
				if o==Orientation::HORIZONTAL
					inc_row=0
					inc_col=1
				else
					inc_row=1
					inc_col=0
				end
				while pos_ok(sr, sc) && empty_pos(sr, sc) && l>0
					@character[sr][sc]=@@BLOCK_CHAR
					l--
					sr+=inc_row
					sc+=inc_col
				end
			end
			
			def valid_moves(r,c)
				output = []
				if can_step_on(r+1, c)
					output.push(Directions::DOWN)
				end
				if can_step_on(r-1, c)
					output.push(Directions::UP)
				end
				if can_step_on(r, c+1)
					output.push(Directions::RIGHT)
				end
				if can_step_on(r, c-1)
					output.push(Directions::LEFT)
				end
				return output
			end
	end
end
	
