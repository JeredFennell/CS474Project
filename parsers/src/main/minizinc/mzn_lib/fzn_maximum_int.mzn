%-----------------------------------------------------------------------------%
% Constrains 'm' to be the maximum of the values in 'x'.
% Assumptions: |x| > 0.
%-----------------------------------------------------------------------------%

predicate fzn_maximum_int(var int: m, array[int] of var int: x);

