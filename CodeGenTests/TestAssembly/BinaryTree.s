   .data
BT$$:
   .quad BT$Start
Tree$$:
   .quad Tree$Delete
   .quad Tree$SetHas_Left
   .quad Tree$RemoveLeft
   .quad Tree$GetKey
   .quad Tree$SetRight
   .quad Tree$GetLeft
   .quad Tree$GetRight
   .quad Tree$Remove
   .quad Tree$SetLeft
   .quad Tree$Insert
   .quad Tree$Print
   .quad Tree$Init
   .quad Tree$GetHas_Right
   .quad Tree$GetHas_Left
   .quad Tree$RemoveRight
   .quad Tree$Search
   .quad Tree$SetKey
   .quad Tree$Compare
   .quad Tree$SetHas_Right
   .quad Tree$RecPrint
   .globl asm_main
asm_main:
   pushq %rbp
   movq %rsp, %rbp
   movq $8, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq BT$$, %rdx
   movq %rdx, 0(%rax)
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *0(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
BT$Start:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   pushq $0
   pushq $0
   movq $56, %rdi
   pushq %rbx
   call mjcalloc
   popq %rbx
   leaq Tree$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -16(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $16, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *88(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *80(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq $100000000, %rax
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -16(%rbp), %rax
   pushq %rax
   movq $8, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *72(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *80(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $24, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *72(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $4, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *72(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $12, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *72(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $20, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *72(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $28, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *72(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $14, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *72(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *80(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $24, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *120(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -16(%rbp), %rax
   pushq %rax
   movq $12, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *120(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -16(%rbp), %rax
   pushq %rax
   movq $16, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *120(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -16(%rbp), %rax
   pushq %rax
   movq $50, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *120(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -16(%rbp), %rax
   pushq %rax
   movq $12, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *120(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq -16(%rbp), %rax
   pushq %rax
   movq $12, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *0(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *80(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $12, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *120(%rax)
   popq %rbx
   movq %rax, %rdi
   pushq %rbx
   call put
   popq %rbx
   movq $0, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$Init:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 48(%rbx)
   movq $0, %rax
   movq -8(%rbp), %rbx
   movq %rax, 40(%rbx)
   movq $0, %rax
   movq -8(%rbp), %rbx
   movq %rax, 16(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$SetRight:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 32(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$SetLeft:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 8(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$GetRight:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 32(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$GetLeft:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 8(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$GetKey:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 48(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$SetKey:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 48(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$GetHas_Right:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 16(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$GetHas_Left:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   movq -8(%rbp), %rbx
   movq 40(%rbx), %rax
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$SetHas_Left:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 40(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$SetHas_Right:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   movq -16(%rbp), %rax
   movq -8(%rbp), %rbx
   movq %rax, 16(%rbx)
   movq $1, %rax
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$Compare:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq $0
   pushq $0
   movq $0, %rax
   movq %rax, -32(%rbp)
   movq -24(%rbp), %rax
   pushq %rax
   movq $1, %rax
   popq %rdx
   addq %rdx, %rax
   movq %rax, -40(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L0
   movq $0, %rax
   movq %rax, -32(%rbp)
   jmp done$L1
else$L0:
   movq -16(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   xorq $1, %rax
   cmpq $0, %rax
   je else$L2
   movq $0, %rax
   movq %rax, -32(%rbp)
   jmp done$L3
else$L2:
   movq $1, %rax
   movq %rax, -32(%rbp)
done$L3:
done$L1:
   movq -32(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$Insert:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq $56, %rdi
   call mjcalloc
   leaq Tree$$, %rdx
   movq %rdx, 0(%rax)
   movq %rax, -24(%rbp)
   movq -24(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *88(%rax)
   movq %rax, -32(%rbp)
   movq -8(%rbp), %rax
   movq %rax, -56(%rbp)
   movq $1, %rax
   movq %rax, -40(%rbp)
test$L4:
   movq -40(%rbp), %rax
   cmpq $0, %rax
   je done$L5
   movq -56(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   movq %rax, -48(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -48(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L6
   movq -56(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *104(%rax)
   cmpq $0, %rax
   je else$L8
   movq -56(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *40(%rax)
   movq %rax, -56(%rbp)
   jmp done$L9
else$L8:
   movq $0, %rax
   movq %rax, -40(%rbp)
   movq -56(%rbp), %rax
   pushq %rax
   movq $1, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *8(%rax)
   movq %rax, -32(%rbp)
   movq -56(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *64(%rax)
   movq %rax, -32(%rbp)
done$L9:
   jmp done$L7
else$L6:
   movq -56(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *96(%rax)
   cmpq $0, %rax
   je else$L10
   movq -56(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *48(%rax)
   movq %rax, -56(%rbp)
   jmp done$L11
else$L10:
   movq $0, %rax
   movq %rax, -40(%rbp)
   movq -56(%rbp), %rax
   pushq %rax
   movq $1, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *144(%rax)
   movq %rax, -32(%rbp)
   movq -56(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *32(%rax)
   movq %rax, -32(%rbp)
done$L11:
done$L7:
   jmp test$L4
done$L5:
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$Delete:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq -8(%rbp), %rax
   movq %rax, -24(%rbp)
   movq -8(%rbp), %rax
   movq %rax, -32(%rbp)
   movq $1, %rax
   movq %rax, -40(%rbp)
   movq $0, %rax
   movq %rax, -48(%rbp)
   movq $1, %rax
   movq %rax, -56(%rbp)
test$L12:
   movq -40(%rbp), %rax
   cmpq $0, %rax
   je done$L13
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   movq %rax, -64(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -64(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L14
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *104(%rax)
   cmpq $0, %rax
   je else$L16
   movq -24(%rbp), %rax
   movq %rax, -32(%rbp)
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *40(%rax)
   movq %rax, -24(%rbp)
   jmp done$L17
else$L16:
   movq $0, %rax
   movq %rax, -40(%rbp)
done$L17:
   jmp done$L15
else$L14:
   movq -64(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L18
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *96(%rax)
   cmpq $0, %rax
   je else$L20
   movq -24(%rbp), %rax
   movq %rax, -32(%rbp)
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *48(%rax)
   movq %rax, -24(%rbp)
   jmp done$L21
else$L20:
   movq $0, %rax
   movq %rax, -40(%rbp)
done$L21:
   jmp done$L19
else$L18:
   movq -56(%rbp), %rax
   cmpq $0, %rax
   je else$L22
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *96(%rax)
   xorq $1, %rax
   cmpq $0, %rax
   je done$L26
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *104(%rax)
   xorq $1, %rax
done$L26:
   cmpq $0, %rax
   je else$L24
   movq $1, %rax
   movq %rax, -72(%rbp)
   jmp done$L25
else$L24:
   movq -8(%rbp), %rax
   pushq %rax
   movq -32(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *56(%rax)
   movq %rax, -72(%rbp)
done$L25:
   jmp done$L23
else$L22:
   movq -8(%rbp), %rax
   pushq %rax
   movq -32(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *56(%rax)
   movq %rax, -72(%rbp)
done$L23:
   movq $1, %rax
   movq %rax, -48(%rbp)
   movq $0, %rax
   movq %rax, -40(%rbp)
done$L19:
done$L15:
   movq $0, %rax
   movq %rax, -56(%rbp)
   jmp test$L12
done$L13:
   movq -48(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$Remove:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq $0
   pushq $0
   pushq $0
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *104(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L27
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *16(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   jmp done$L28
else$L27:
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *96(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L29
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *112(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   jmp done$L30
else$L29:
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, -40(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *40(%rax)
   popq %rbx
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, -48(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   movq -40(%rbp), %rax
   pushq %rax
   movq -48(%rbp), %rax
   pushq %rax
   popq %rdx
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *136(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L31
   movq -16(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *64(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *8(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   jmp done$L32
else$L31:
   movq -16(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *144(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
done$L32:
done$L30:
done$L28:
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$RemoveRight:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq $0
test$L33:
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *96(%rax)
   popq %rbx
   cmpq $0, %rax
   je done$L34
   movq -24(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *48(%rax)
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *128(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -24(%rbp), %rax
   movq %rax, -16(%rbp)
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *48(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   jmp test$L33
done$L34:
   movq -16(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *32(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *144(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$RemoveLeft:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq %rdx
   pushq $0
test$L35:
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *104(%rax)
   popq %rbx
   cmpq $0, %rax
   je done$L36
   movq -24(%rbp), %rax
   pushq %rax
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *40(%rax)
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *128(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -24(%rbp), %rax
   movq %rax, -16(%rbp)
   movq -24(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *40(%rax)
   popq %rbx
   movq %rax, -24(%rbp)
   jmp test$L35
done$L36:
   movq -16(%rbp), %rax
   pushq %rax
   movq -8(%rbp), %rbx
   movq 24(%rbx), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *64(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq $0, %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *8(%rax)
   popq %rbx
   movq %rax, -32(%rbp)
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$Search:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   pushq $0
   pushq $0
   pushq $0
   movq -8(%rbp), %rax
   movq %rax, -40(%rbp)
   movq $1, %rax
   movq %rax, -24(%rbp)
   movq $0, %rax
   movq %rax, -32(%rbp)
test$L37:
   movq -24(%rbp), %rax
   cmpq $0, %rax
   je done$L38
   movq -40(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *24(%rax)
   popq %rbx
   movq %rax, -48(%rbp)
   movq -16(%rbp), %rax
   pushq %rax
   movq -48(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L39
   movq -40(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *104(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L41
   movq -40(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *40(%rax)
   popq %rbx
   movq %rax, -40(%rbp)
   jmp done$L42
else$L41:
   movq $0, %rax
   movq %rax, -24(%rbp)
done$L42:
   jmp done$L40
else$L39:
   movq -48(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   popq %rdx
   movq $0, %rbx
   movq $1, %rcx
   cmpq %rax, %rdx
   cmovl %rcx, %rbx
   movq %rbx, %rax
   cmpq $0, %rax
   je else$L43
   movq -40(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *96(%rax)
   popq %rbx
   cmpq $0, %rax
   je else$L45
   movq -40(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *48(%rax)
   popq %rbx
   movq %rax, -40(%rbp)
   jmp done$L46
else$L45:
   movq $0, %rax
   movq %rax, -24(%rbp)
done$L46:
   jmp done$L44
else$L43:
   movq $1, %rax
   movq %rax, -32(%rbp)
   movq $0, %rax
   movq %rax, -24(%rbp)
done$L44:
done$L40:
   jmp test$L37
done$L38:
   movq -32(%rbp), %rax
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$Print:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq $0
   pushq $0
   movq -8(%rbp), %rax
   movq %rax, -16(%rbp)
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *152(%rax)
   movq %rax, -24(%rbp)
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
Tree$RecPrint:
   pushq %rbp
   movq %rsp, %rbp
   pushq %rdi
   pushq %rsi
   pushq $0
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *104(%rax)
   cmpq $0, %rax
   je else$L47
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *40(%rax)
   popq %rbx
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *152(%rax)
   movq %rax, -24(%rbp)
   jmp done$L48
else$L47:
   movq $1, %rax
   movq %rax, -24(%rbp)
done$L48:
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *24(%rax)
   movq %rax, %rdi
   call put
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   call *96(%rax)
   cmpq $0, %rax
   je else$L49
   movq -8(%rbp), %rax
   pushq %rax
   movq -16(%rbp), %rax
   pushq %rax
   popq %rdi
   movq 0(%rdi), %rax
   pushq %rbx
   call *48(%rax)
   popq %rbx
   pushq %rax
   popq %rsi
   popq %rdi
   movq 0(%rdi), %rax
   call *152(%rax)
   movq %rax, -24(%rbp)
   jmp done$L50
else$L49:
   movq $1, %rax
   movq %rax, -24(%rbp)
done$L50:
   movq $1, %rax
   popq %rbx
   popq %rbx
   popq %rbx
   movq %rbp, %rsp
   popq %rbp
   ret
