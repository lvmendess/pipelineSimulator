public class Dicionario {
    
    public Instruction search(String[] inst){
        Instruction i = null;
        /* tipo I - load */
        if(inst[0].equals("lb")||inst[0].equals("lh")||inst[0].equals("lwl")||
        inst[0].equals("lw")||inst[0].equals("lbu")||inst[0].equals("lhu")||
        inst[0].equals("lwr")){

            i = new typeI(inst[0], inst[1], inst[3], inst[4], false);
        }
        /* tipo R */
        else if(inst[0].equals("add")||inst[0].equals("addu")||inst[0].equals("sub")
        ||inst[0].equals("subu")||inst[0].equals("and")||inst[0].equals("or")
        ||inst[0].equals("xor")||inst[0].equals("nor")||inst[0].equals("slt")
        ||inst[0].equals("sltu")||inst[0].equals("sll")||inst[0].equals("srl")
        ||inst[0].equals("sra")||inst[0].equals("srav")||inst[0].equals("sllv")
        ||inst[0].equals("srlv")){

            i = new typeR(inst[0], inst[1], inst[2], inst[3], inst[4], true);
        }

        else if(inst[0].equals("addi")||inst[0].equals("addiu")||inst[0].equals("slti")
        ||inst[0].equals("sltiu")||inst[0].equals("andi")||inst[0].equals("ori")
        ||inst[0].equals("xori")||inst[0].equals("lui")){

            i = new typeI(inst[0], inst[1], inst[2], inst[4], true);
        }

        else if(inst[0].equals("sb")||inst[0].equals("sh")||inst[0].equals("swl")||
        inst[0].equals("sw")||inst[0].equals("swr")){

            i = new typeI(inst[0], inst[3], inst[1], inst[4], true);
        }

        else if(inst[0].equals("mfhi")||inst[0].equals("mflo")){
            i = new typeR(inst[0], inst[1], null, null, inst[2], true);
        }
        
        else if(inst[0].equals("mthi")||inst[0].equals("mtlo")){

            i = new typeR(inst[0], null, inst[1], null, inst[2], true);
        }

        else if(inst[0].equals("mult")||inst[0].equals("multu")||
        inst[0].equals("div")||inst[0].equals("divu")){

            i = new typeI(inst[0], inst[1], inst[2], inst[3], true);
        }

        else if(inst[0].equals("bltz")||inst[0].equals("bgez")||inst[0].equals("bltzal")
        ||inst[0].equals("bgezal")||inst[0].equals("blez")||inst[0].equals("bgtz")){

            i = new typeI(inst[0], inst[1], inst[2], inst[3], true);
        }

        else if(inst[0].equals("jr")||inst[0].equals("jalr")||inst[0].equals("j")
        ||inst[0].equals("jal")){

            i = new typeJ(inst[0], inst[1], inst[2], true);
        }

        else if(inst[0].equals("beq")||inst[0].equals("bne")){

            i = new typeI(inst[0], inst[1], inst[2], inst[4], true);
        }

        else if(inst[0].equals("NOP")){
            i = new Bolha();
        }

        return i;
    }
}
